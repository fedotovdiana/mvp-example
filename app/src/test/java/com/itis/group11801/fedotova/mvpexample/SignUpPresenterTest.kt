package com.itis.group11801.fedotova.mvpexample

import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import com.itis.group11801.fedotova.mvpexample.ui.signup.SignUpPresenter
import com.itis.group11801.fedotova.mvpexample.ui.signup.`SignUpView$$State`
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class SignUpPresenterTest {

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mockViewState: `SignUpView$$State`

    @MockK
    lateinit var mockUserRepository: UserRepository

    lateinit var presenter: SignUpPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        presenter = spyk(SignUpPresenter(mockUserRepository))
        presenter.setViewState(mockViewState)
    }

    @Test
    fun signUpFail() {
        coEvery { mockUserRepository.signUpEmailAndPassword(any(), any()) } returns false
        every { mockViewState.showToast(any()) } just Runs

        val email = "email@mail.ru"
        val password = "password"
        presenter.signUp(email, password)

        verify { mockViewState.showToast(any()) }
    }

    @Test
    fun signUpSuccess() {
        coEvery { (mockUserRepository.signUpEmailAndPassword(any(), any())) } returns true
        every { mockViewState.showToast(any()) } just Runs
        every { mockViewState.navigateToSignIn() } just Runs

        val email = "email@mail.ru"
        val password = "password"
        presenter.signUp(email, password)

        verify { mockViewState.showToast(any()) }
        verify { mockViewState.navigateToSignIn() }
    }
}
