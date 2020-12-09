package com.esaldivia.melichallenge.ui.searchitems


import android.content.Context
import android.os.Build
import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.esaldivia.melichallenge.getOrAwaitValue
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.model.SearchResponse
import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import com.esaldivia.melichallenge.repository.Repository
import com.esaldivia.melichallenge.utils.Resource
import com.esaldivia.melichallenge.utils.Status
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.LooperMode.Mode.PAUSED


@RunWith(AndroidJUnit4::class)
@LooperMode(PAUSED)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SearchItemViewModelTest {

    // Region constants

    // EndRegion constants

    // Region helper fields

    private val itemApiFake: ItemApiFake = ItemApiFake()
    lateinit var repository: RepositoryFake

    // EndRegion helper fields
    lateinit var SUT: SearchItemViewModel
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = RepositoryFake(itemApiFake)
        SUT = SearchItemViewModel(repository, ApplicationProvider.getApplicationContext())

    }

    @Test
    fun searchItem_SUCCES_fetchedSearchResponse(){
        SUT = SearchItemViewModel(repository, ApplicationProvider.getApplicationContext())

        val value = SUT.searchItem("TEST").getOrAwaitValue()
        shadowOf(Looper.getMainLooper()).idle()

        assertThat(value.status, `is`(Status.SUCCESS))
        assertThat(value.data!!.itemList.size, `is`(1))
        assertThat(value.message, nullValue())

    }

    @Test
    fun searchItem_ERROR_retrieveError(){
        ERROR()

        val value = SUT.searchItem("TEST").getOrAwaitValue()
        shadowOf(Looper.getMainLooper()).idle()

        assertThat(value.status, `is`(Status.ERROR))
        assertThat(value.message, not(nullValue()))

    }

    @Test
    fun searchItem_LOADING(){
        LOADING()

        val value = SUT.searchItem("TEST").getOrAwaitValue()
        shadowOf(Looper.getMainLooper()).idle()

        assertThat(value.status, `is`(Status.LOADING))
        assertThat(value.data, nullValue())

    }

    // Region helper methods
    fun ERROR() {
        repository.isError = true
    }

    fun LOADING() {
        repository.isLoading = true
    }

    // Endregion helper methods

    // Region helper classes
    class ItemApiFake : SearchItemApi {
        override suspend fun searchItemByName(siteId: String, name: String): SearchResponse {
            val items: ArrayList<Item> = arrayListOf(Item("id", "name", "url"))
            return SearchResponse(items)
        }

    }

    class RepositoryFake(itemApiFake: SearchItemApi) : Repository(itemApiFake) {
        var isError = false
        var isLoading = false
        override fun renombrar(name: String, context: Context) = liveData {
            if (isError) {
                emit(Resource.error(null, message = "ERROR MESSAGE"))
            } else if (isLoading){
                emit(Resource.loading(null))
            } else {
                emit(Resource.succces(itemApi.searchItemByName("TEST_ID", name)))
            }
        }
    }

    // Endregion helper classes
}