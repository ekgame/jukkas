/*
 * Copyright 2023 Oliver Berg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.ormr.jukkas.ast

import net.ormr.jukkas.Position
import net.ormr.jukkas.Source
import net.ormr.jukkas.StructurallyComparable
import net.ormr.jukkas.reporter.MessageReporter
import net.ormr.jukkas.type.TypeCache
import net.ormr.jukkas.utils.checkStructuralEquivalence

class CompilationUnit(
    val source: Source,
    override val position: Position,
    imports: List<Import>,
    children: List<TopLevel>,
    override val table: Table,
) : AbstractNode(), TableContainer {
    val imports: MutableNodeList<Import> = imports.toMutableNodeList(this)
    val types: TypeCache = TypeCache(this)
    val reporter: MessageReporter = MessageReporter()
    val children: MutableNodeList<TopLevel> = children.toMutableNodeList(this, ::handleAddChild, ::handleRemoveChild)
    override val compilationUnit: CompilationUnit
        get() = this

    override fun isStructurallyEquivalent(other: StructurallyComparable): Boolean =
        other is CompilationUnit &&
                checkStructuralEquivalence(imports, other.imports) &&
                checkStructuralEquivalence(children, other.children)
}