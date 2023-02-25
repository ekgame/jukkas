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
import net.ormr.jukkas.type.TypeOrError
import net.ormr.jukkas.utils.checkStructuralEquivalence

class LocalVariable(
    override val kind: PropertyKind,
    override val name: String,
    type: TypeName,
    initializer: Expression?,
) : AbstractStatement(), Variable {
    override val type: TypeName by child(type)
    override var initializer: Expression? by child(initializer)
    var index: Int = -1

    override fun isStructurallyEquivalent(other: StructurallyComparable): Boolean =
        other is LocalVariable &&
            kind == other.kind &&
            name == other.name &&
            checkStructuralEquivalence(initializer, other.initializer) &&
            type.isStructurallyEquivalent(other.type)

    operator fun component1(): PropertyKind = kind

    operator fun component2(): String = name

    operator fun component3(): TypeOrError? = findTypeName().resolvedType

    operator fun component4(): Expression? = initializer
}