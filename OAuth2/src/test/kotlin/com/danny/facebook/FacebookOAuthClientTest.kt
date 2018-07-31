package com.danny.facebook

import com.danny.core.OAuthClientConfig
import com.danny.core.OAuthConstant
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Before
import org.apache.http.HttpEntity
import org.apache.http.StatusLine
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients

class FacebookOAuthClientTest{

    companion object {
        const val LOGIN_DIALOG = "https://www.facebook.com:443/v3.1/dialog/oauth?"
        const val TEST_AUTHORIZATION_CODE = "wertyuiop"
        const val TEST_CLIENT_ID = "123"
        const val TEST_CLIENT_SECRET = "abc"
        const val TEST_CLIENT_SCOPE = "openid"
        const val TEST_CLIENT_REDIRECT_URI = "danny.com"
        const val EQUAL_MARK = "="
        const val AND_MARK = "&"
        const val TEST_ACCESS_TOKEN_RESPONSE = "{\"access_token\": abc, \"token_type\": \"bearer\", \"expires_in\": 123456}"
    }

    private lateinit var config: OAuthClientConfig

    @MockK
    private lateinit var mockHttpClient: CloseableHttpClient

    @MockK
    private lateinit var mockResponse: CloseableHttpResponse

    @MockK
    private lateinit var mockStatusLine: StatusLine

    @MockK
    private lateinit var mockEntity: HttpEntity

    @Before
    fun setup(){
        config = OAuthClientConfig.setClientId(TEST_CLIENT_ID).setClientSecret(TEST_CLIENT_SECRET)
                .setScope(TEST_CLIENT_SCOPE).setRedirectUri(TEST_CLIENT_REDIRECT_URI).build()

        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun testAuthorizeUser(){
        val uri = FacebookOAuthClient().authorizeUser(config)
        val expect = StringBuilder(LOGIN_DIALOG)
                .append(OAuthConstant.CLIENT_ID)
                .append(EQUAL_MARK)
                .append(TEST_CLIENT_ID)
                .append(AND_MARK)
                .append(OAuthConstant.SCOPE)
                .append(EQUAL_MARK)
                .append(TEST_CLIENT_SCOPE)
                .append(AND_MARK)
                .append(OAuthConstant.REDIRECT_URI)
                .append(EQUAL_MARK)
                .append(TEST_CLIENT_REDIRECT_URI)
        assertEquals(expect.toString(), uri)
    }

    @Test
    fun testNewTokenRequest(){
        mockkStatic(HttpClients::class)
        every { HttpClients.createDefault() } returns mockHttpClient
        every { mockHttpClient.execute(any()) } returns mockResponse
        every { mockResponse.statusLine } returns mockStatusLine
        every { mockResponse.entity } returns mockEntity
        every { mockStatusLine.statusCode } returns 200
        every { mockEntity.content } returns TEST_ACCESS_TOKEN_RESPONSE.byteInputStream()
        every { mockEntity.contentType } returns null
        every { mockEntity.contentLength } returns 0

        val result = FacebookOAuthClient().newTokenRequest(config, TEST_AUTHORIZATION_CODE)
        assertEquals(TEST_ACCESS_TOKEN_RESPONSE, result)
    }
}