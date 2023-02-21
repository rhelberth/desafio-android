package com.picpay.desafio.android.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.repository.FakeUserRepository
import com.picpay.desafio.android.data.repository.di.RepositoryModule
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.activity.MainActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(RepositoryModule::class)
class HomeFragmentTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @BindValue
    @JvmField
    val repository = FakeUserRepository()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem(): Unit = runBlocking {
        repository.resultUser = ResultStatus.Success(listUser)

        launchActivity<MainActivity>().apply {
            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))


            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.recyclerView,
                0,
                withText("@fulano")
            )
        }
    }

    @Test
    fun shouldHideRecycleViewWhenListIsEmpty(): Unit = runBlocking {
        repository.resultUser = ResultStatus.Error(Throwable())

        launchActivity<MainActivity>().apply {
            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))

            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
    }


    private val listUser = listOf(
        User(
            img = "https://randomuser.me/img01",
            name = "Fulado",
            id = 1,
            username = "@fulano"
        ),
        User(
            img = "https://randomuser.me/img02",
            name = "Ciclano",
            id = 2,
            username = "@ciclano"
        )
    )
}