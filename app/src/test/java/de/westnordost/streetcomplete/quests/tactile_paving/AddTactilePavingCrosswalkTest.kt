package de.westnordost.streetcomplete.quests.tactile_paving

import de.westnordost.osmapi.map.data.OsmNode
import de.westnordost.osmapi.map.data.OsmWay
import de.westnordost.streetcomplete.quests.TestMapDataWithGeometry
import org.junit.Assert.*
import org.junit.Test

class AddTactilePavingCrosswalkTest {
    private val questType = AddTactilePavingCrosswalk()

    @Test fun `applicable to crossing`() {
        val mapData = TestMapDataWithGeometry(listOf(
            OsmNode(1L, 1, 0.0,0.0, mapOf(
                "highway" to "crossing"
            ))
        ))
        assertEquals(1, questType.getApplicableElements(mapData).toList().size)
    }

    @Test fun `not applicable to crossing with private road`() {
        val mapData = TestMapDataWithGeometry(listOf(
            OsmNode(1L, 1, 0.0,0.0, mapOf(
                "highway" to "crossing"
            )),
            OsmWay(1L, 1, listOf(1,2,3), mapOf(
                "highway" to "residential",
                "access" to "private"
            ))
        ))
        assertEquals(0, questType.getApplicableElements(mapData).toList().size)
    }
}