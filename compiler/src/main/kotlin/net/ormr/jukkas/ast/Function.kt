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

import net.ormr.jukkas.StructurallyComparable
import net.ormr.jukkas.type.Type
import net.ormr.jukkas.utils.checkStructuralEquivalence

class Function(
    override val name: String,
    arguments: List<Argument>,
    body: Block?,
    override var type: Type,
    override val table: Table,
) : Statement(), Invokable, NamedDefinition, TableContainer {
    override val arguments: MutableNodeList<Argument> =
        arguments.toMutableNodeList(this, ::handleAddChild, ::handleRemoveChild)
    override var body: Block? by child(body)

    override fun <T> accept(visitor: NodeVisitor<T>): T = visitor.visitFunction(this)

    override fun isStructurallyEquivalent(other: StructurallyComparable): Boolean =
        other is Function &&
                name == other.name &&
                checkStructuralEquivalence(arguments, other.arguments) &&
                checkStructuralEquivalence(body, other.body) &&
                type.isStructurallyEquivalent(other.type)

    override fun toString(): String = "Function(name='$name', type=$type, arguments=$arguments, body=$body)"
}