package io.github.hardikvaghani.flexiscale.generator.generator

import io.github.hardikvaghani.flexiscale.generator.model.DimensionEntry

fun interface DimensionGenerator {

    fun generate(): List<DimensionEntry>
}