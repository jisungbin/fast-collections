package land.sungbin.fastlist.ksp

import com.google.auto.service.AutoService
import com.google.devtools.ksp.getVisibility
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.Visibility
import com.google.devtools.ksp.validate
import land.sungbin.gfm.markdown
import land.sungbin.gfm.tag.code
import land.sungbin.gfm.tag.table

@Suppress("unused")
@AutoService(SymbolProcessorProvider::class)
class KspEntryPoint : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    return SymbolProcessorProvider(
      codeGenerator = environment.codeGenerator,
      logger = environment.logger,
    )
  }
}

private typealias ParamName = String
private typealias ParamType = String

private class SymbolProcessorProvider(
  private val codeGenerator: CodeGenerator,
  private val logger: KSPLogger,
) : SymbolProcessor {
  private var invoked = false

  private data class Fn(
    val doc: String?,
    val name: String,
    val receiver: String?,
    val params: List<Pair<ParamName, ParamType>>,
    val returnType: String,
  ) {
    val signature = buildString {
      append("fun")
      append(" ")
      if (receiver != null) append("$receiver.")
      append(name)
      append("(")

      val paramIterator = params.iterator()
      while (paramIterator.hasNext()) {
        val (name, type) = paramIterator.next()
        append("$name: $type")
        if (paramIterator.hasNext()) append(", ")
      }

      append(")")
      append(":")
      append(" ")
      append(returnType)
    }
  }

  private fun log(src: Any) {
    logger.warn(src.toString())
  }

  override fun process(resolver: Resolver): List<KSAnnotated> {
    if (invoked) return emptyList()

    val kt = resolver.getAllFiles().single()
    val fnDeclarations = kt.declarations.filterIsInstance<KSFunctionDeclaration>()

    log(kt.fileName)
    log(fnDeclarations.joinToString { fn -> fn.simpleName.asString() })

    val fns = mutableListOf<Fn>()

    for (fn in fnDeclarations) {
      if (fn.getVisibility() != Visibility.PUBLIC) continue

      val doc = fn.docString?.trimIndent()?.replace("\n", "<br/>")
      val name = fn.simpleName.asString()
      val receiver = fn.extensionReceiver?.resolve()?.declaration?.simpleName?.asString()
      val params = fn.parameters.map { param ->
        param.name!!.asString() to param.type.resolve().declaration.simpleName.asString()
      }
      val returnType =
        fn.returnType?.resolve()?.declaration?.simpleName?.asString() ?: resolver.builtIns.unitType.declaration.simpleName.asString()

      fns += Fn(doc = doc, name = name, receiver = receiver, params = params, returnType = returnType).also(::log)
    }

    val md = markdown {
      +table {
        +header {
          +"Doc"
          +"Signature"
        }
        for (fn in fns) {
          +body {
            +(fn.doc ?: "-")
            +code(fn.signature)
          }
        }
      }
    }

    log(md)
    codeGenerator
      .createNewFile(
        dependencies = Dependencies(aggregating = false),
        packageName = "",
        fileName = "functions",
        extensionName = "md"
      )
      .writer()
      .use { writer ->
        writer.append(md.toString())
      }
    log("Saved: ${codeGenerator.generatedFile.single().path}")

    invoked = true
    return fnDeclarations.filterNot(KSAnnotated::validate).toList()
  }
}

