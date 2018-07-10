/*
 * Copyright 2018 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.boothub.slidedeck

import org.boothub.context.ProjectContext

import java.nio.file.Paths
import org.boothub.Initializr
import org.boothub.gradle.*
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class SlideDeckTemplateSpec extends Specification {

    private static final String TEMPLATE_DIR = getPath("/template")

    private static final String BASE_PATH = 'org/bizarre_soft'

private static final String CONTEXT_SINGLE = getPath("/base-context.yml")


    private static String getPath(String resourcePath) {
        def resource = SlideDeckTemplateSpec.class.getResource(resourcePath)
        assert resource : "Resource not available: $resourcePath"
        Paths.get(resource.toURI()).toAbsolutePath().toString()
    }

def "should create a valid artifact using base-context.yml"() {
        when:
        def artifacts = new GradleTemplateBuilder(TEMPLATE_DIR)
                .withContextFile(CONTEXT_SINGLE)
                .runGradleBuild()
                .artifacts
        def jars = artifacts['jar']

        then:
        jars.size() == 1
    }

    private static Collection<ProjectContext> getAsciiDocContexts(String contextFile) {
        def builder = new ProjectContextStreamBuilder({
            new Initializr(TEMPLATE_DIR).createContext(getPath("/$contextFile"))
        }).withFlagNames('language')
        .withExclusion{ctx -> ctx.language.name == 'Kotlin'} // TODO - fix Kotlin build and remove this line
        builder.stream().collect(Collectors.toList())
    }

    private static boolean isValidSlideDeck(ProjectContext context) {
        def builder = new GradleTemplateBuilder(TEMPLATE_DIR).withContext(context).withInProcessBuild(true).withGradleOptions('--debug', '--stacktrace')
        def taskName = builder.getQualifiedTaskName(null, 'asciidoctor')
        def gradleResult = builder.runGradle(taskName)
        assert builder.checkTask(taskName, gradleResult)
        def docDir = gradleResult.projectPath.resolve('build/asciidoc/html5').toFile()
        assert docDir.directory
        assert docDir.list() as Set == ['img', 'deck.js', 'weirdo.html'] as Set
        true
    }

    @Unroll
    def "should create slide deck using #flags"() {
        expect:
        isValidSlideDeck(context)

        where:
        context << getAsciiDocContexts('base-context.yml')
        flags = context.dumpFlags()
    }

}
