package io.skysail.gradle.plugin.twirlosgi

import org.gradle.api.{Plugin, Project}
import org.slf4j.LoggerFactory


class TwirlPlugin extends Plugin[Project] {

  val logger = LoggerFactory.getLogger("gradlebuild")

  override def apply(project: Project) = {
    logger.info(" > " + project.getProjectDir)
    println(" >>> compiling twirl sources...")
    val compiler = new io.skysail.gradle.plugin.twirlosgi.SkysailTwirlCompiler(project.getProjectDir)
    println(" > " + compiler)
    compiler.compileDir()
    logger.info("compiled")
  }
}
