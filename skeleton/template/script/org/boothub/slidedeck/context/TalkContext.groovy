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
package org.boothub.slidedeck.context

import groovy.transform.SelfType
import org.boothub.context.ConfiguredBy
import org.boothub.context.ProjectContext
import org.boothub.context.TextIOConfigurator
import org.beryx.textio.TextIO

@SelfType(ProjectContext)
@ConfiguredBy(TalkContext.Configurator)
trait TalkContext {
    static enum Language {
        JAVA('Java'), GROOVY('Groovy'), KOTLIN('Kotlin')

        final String name

        Language(String name) {
            this.name = name
        }

        @Override
        String toString() { name }
    }

    String codeSnippetsDir
    Language language = Language.JAVA
    String talkName

    boolean isUseJava() { language == Language.JAVA }
    boolean isUseGroovy() { language == Language.GROOVY}
    boolean isUseKotlin() { language == Language.KOTLIN}

    static class Configurator extends TextIOConfigurator  {
        @Override
        void configureWithTextIO(ProjectContext context, TextIO textIO) {
            def ctx = context as TalkContext
            ctx.codeSnippetsDir = context.modules[0].basePackage.replace('.', '/')
            ctx.language = textIO.newEnumInputReader(Language)
                    .withDefaultValue(ctx.language)
                    .read("Which language should be used for the code snippets?")
            ctx.talkName = textIO.newStringInputReader()
                    .withDefaultValue(ctx.projectName)
                    .withMaxLength(100)
                    .read("Presentation title")
        }
    }
}
