package com.danny.facebook

import com.danny.core.OAuthClientConfig
import com.danny.core.OAuthFactory

class FacebookOAuthFactory: OAuthFactory{

    val LOGIN_DIALOG = "https://www.facebook.com/v3.1/dialog/oauth"
    val GRAPH_API = "https://graph.facebook.com/v3.1/oauth/access_token"

    override fun authorizationUri(client: OAuthClientConfig): String{
        return LOGIN_DIALOG
    }

    override fun accessTokenUri(client: OAuthClientConfig, authorizationCode: String): String{
        return GRAPH_API
    }
}