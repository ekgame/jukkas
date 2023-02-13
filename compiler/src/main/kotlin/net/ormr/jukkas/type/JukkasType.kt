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

package net.ormr.jukkas.type

class JukkasType private constructor(val name: String) : ResolvedType {
    override val packageName: String
        get() = TODO("Not yet implemented")
    override val simpleName: String
        get() = TODO("Not yet implemented")
    override val internalName: String
        get() = TODO("Not yet implemented")

    override fun toJvmDescriptor(): String {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other !is JukkasType -> false
        name != other.name -> false
        else -> true
    }

    override fun hashCode(): Int = name.hashCode()

    companion object {
        val UNIT = JukkasType("jukkas.Unit")
    }
}