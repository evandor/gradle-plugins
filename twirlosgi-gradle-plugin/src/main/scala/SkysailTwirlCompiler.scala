package io.skysail.gradle.plugin.twirlosgi

import java.io.File

import org.slf4j.LoggerFactory
import play.twirl.compiler.TwirlCompiler

import scala.io.Codec

// https://github.com/csokol/vraptor-twirl/blob/master/src/main/java/br/com/caelum/vraptor/twirl/Compiler.java
class SkysailTwirlCompiler(dir: File) {

  val logger = LoggerFactory.getLogger("gradlebuild")

  private val root = new File(dir, "./src")
  private val outputFolder = new File(dir, "./src")

  logger.info(" > root: " + root.getAbsolutePath.toString)
  logger.info(" > outputFolder: " + outputFolder.getAbsolutePath.toString)

  def main(args: Array[String]) {
    compileDir(root)
  }

  def compileDir(): Unit = compileDir(root)

  def compileDir(viewsFolder: File): Unit = {
    val possibleViews = viewsFolder.listFiles
    for (view <- possibleViews) {
      if (view.isDirectory) compileDir(view) else compile(view)
    }
  }

  var additionalImports: scala.Seq[_root_.scala.Predef.String] = List(
    "play.twirl.api.Html",
    "html.main",
    "io.skysail.domain.ResponseEventBase",
    "io.skysail.server.RepresentationModel"
  )

  var constructorAnnotations: scala.Seq[_root_.scala.Predef.String] = Nil

  def compile(view: File): Unit = {
    if (view.getName.endsWith(".scala.html")) {
      TwirlCompiler.compile(view, root, outputFolder, "play.twirl.api.HtmlFormat", additionalImports, constructorAnnotations, Codec(scala.util.Properties.sourceEncoding), false)
    }
  }
}