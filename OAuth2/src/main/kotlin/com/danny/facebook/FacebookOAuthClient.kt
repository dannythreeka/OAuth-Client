package com.danny.facebook

import com.danny.core.OAuthClient
import com.danny.core.OAuthClientConfig
import com.danny.core.OAuthConst
import com.danny.facebook.FacebookOAuthConst.GRAPH_API_HOST
import com.danny.facebook.FacebookOAuthConst.GRAPH_API_PATH
import com.danny.facebook.FacebookOAuthConst.LOGIN_DIALOG_HOST
import com.danny.facebook.FacebookOAuthConst.LOGIN_DIALOG_PATH
import com.danny.facebook.FacebookOAuthConst.PORT
import com.danny.facebook.FacebookOAuthConst.SCHEME
import com.danny.facebook.exception.FacebookAccessTokenException
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.util.EntityUtils
import org.apache.http.impl.client.HttpClients

/**
 * FACEBOOK OAuth Client which provided the OAuth function
 * For Grant Type: Authorization Code
 *
 * @author Danny Wang <dannythreekai@gmail.com>
 */
class FacebookOAuthClient(private val clientConfig: OAuthClientConfig): OAuthClient {

    override fun getAuthorizationUrl(): String{
        return URIBuilder().setScheme(SCHEME).setHost(LOGIN_DIALOG_HOST).setPort(PORT)
                .setPath(LOGIN_DIALOG_PATH).setParameter(OAuthConst.CLIENT_ID, clientConfig.getClientId())
                .setParameter(OAuthConst.SCOPE, clientConfig.getScope())
                .setParameter(OAuthConst.REDIRECT_URI, clientConfig.getRedirectUri()).build().toString()
    }

    override fun getAccessToken(authorizationCode: String): String{
        val httpGet = HttpGet(getRequestTokenUri(clientConfig, authorizationCode))
        try {
            val httpResponse = HttpClients.createDefault().execute(httpGet)
            if (httpResponse.statusLine.statusCode === 200) {
                val httpEntity = httpResponse.entity
                return  EntityUtils.toString(httpEntity)
            } else
                httpGet.abort()
        } catch (e: Exception) {
            throw FacebookAccessTokenException(e.message.toString())
        }
        return ""
    }

    private fun getRequestTokenUri(client: OAuthClientConfig, authorizationCode: String): String{
        return URIBuilder().setScheme(SCHEME).setHost(GRAPH_API_HOST).setPort(PORT)
                .setPath(GRAPH_API_PATH).setParameter(OAuthConst.CLIENT_ID, client.getClientId())
                .setParameter(OAuthConst.CLIENT_SECRET, client.getClientSecret())
                .setParameter(OAuthConst.REDIRECT_URI, client.getRedirectUri())
                .setParameter(OAuthConst.CODE, authorizationCode).build().toString()
    }

}