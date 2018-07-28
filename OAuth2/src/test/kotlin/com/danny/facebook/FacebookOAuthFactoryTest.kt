package com.danny.facebook

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Before

class FacebookOAuthFactoryTest{

    val LOGIN_DIALOG = "https://www.facebook.com/v3.1/dialog/oauth"
    val GRAPH_API = "https://graph.facebook.com/v3.1/oauth/access_token"
    val TEST_AUTHORIZATION_CODE = "wertyuiop"
    val TEST_CLIENT_KEY = "123"
    val TEST_CLIENT_SECRET = "abc"
    val TEST_CLIENT_SCOPE = "openid"
    val TEST_CLIENT_REDIRECT_URI = "http://danny.com"

    @Before
    fun setup(){
        val config = OAuthClientConfig.setClientKey(TEST_CLIENT_KEY).setClientSecret(TEST_CLIENT_SECRET)
        .setScope(TEST_CLIENT_SCOPE).setRedirectUri(TEST_CLIENT_REDIRECT_URI).build()
    }

    @Test
    fun testAuthorizationUri(){
        val uri = FacebookOAuthFactory().authorizationUri(config)
        assertEquals(LOGIN_DIALOG, uri)
    }

    @Test
    fun testAccessTokenUri(){
        val uri = FacebookOAuthFactory().accessTokenUri(config, TEST_AUTHORIZATION_CODE)
        assertEquals(GRAPH_API, uri)
    }
}