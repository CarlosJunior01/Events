import androidx.test.ext.junit.runners.AndroidJUnit4
import com.carlos.events.usecase.presentation.viewmodels.DetailsViewModelRobot
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventsViewModelTest {

    @After
    fun tearDown() {
        DetailsViewModelRobot.tearDown()
    }

    @Test
    fun `when start viewModel should execute getEvents`(){
        DetailsViewModelRobot.apply {
            arrange{
                mockSuccess()
            }

            act {
                initViewModel()
            }

            assert {
                isGetEvents()
            }
        }
    }

    @Test
    fun `when start viewModel should execute checkIn`(){
        DetailsViewModelRobot.apply {
            arrange{
                mockCheckIn()
            }
            act {
                initViewModel()
            }

            assert {
                isChekIn()
            }
        }
    }
}