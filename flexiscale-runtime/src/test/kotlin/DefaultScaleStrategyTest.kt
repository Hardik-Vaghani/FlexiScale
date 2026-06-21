import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.strategy.DefaultScaleStrategy
import kotlin.test.Test

class DefaultScaleStrategyTest {

    @Test
    fun allBucketsHaveProfile() {

        ScreenBucket.entries.forEach {
            DefaultScaleStrategy.profile(it)
        }
    }
}