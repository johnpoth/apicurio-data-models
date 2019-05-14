/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.openapi.v3.visitors;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.common.IParameterDefinition;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.openapi.models.IOasResponseDefinition;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.v3.models.Oas30AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Components;
import io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30ImplicitOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30OAuthFlows;
import io.apicurio.datamodels.openapi.v3.models.Oas30PasswordOAuthFlow;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;
import io.apicurio.datamodels.openapi.visitors.OasNodePathVisitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas30NodePathVisitor extends OasNodePathVisitor implements IOas30Visitor {
    
    /**
     * Constructor.
     */
    public Oas30NodePathVisitor() {
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.NodePathVisitor#visitSchemaDefinition(io.apicurio.datamodels.core.models.common.ISchemaDefinition)
     */
    @Override
    public void visitSchemaDefinition(ISchemaDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_SCHEMAS, false);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.NodePathVisitor#visitParameterDefinition(io.apicurio.datamodels.core.models.common.IParameterDefinition)
     */
    @Override
    public void visitParameterDefinition(IParameterDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_PARAMETERS, false);
    }
    
    /**
     * @see io.apicurio.datamodels.core.visitors.NodePathVisitor#visitSecurityScheme(io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    public void visitSecurityScheme(SecurityScheme node) {
        this.path.prependSegment(node.getSchemeName(), true);
        this.path.prependSegment(Constants.PROP_SECURITY_SCHEMES, false);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasNodePathVisitor#visitResponseDefinition(io.apicurio.datamodels.openapi.models.IOasResponseDefinition)
     */
    @Override
    public void visitResponseDefinition(IOasResponseDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_RESPONSES, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitComponents(io.apicurio.datamodels.openapi.v3.models.Oas30Components)
     */
    @Override
    public void visitComponents(Oas30Components node) {
        this.path.prependSegment(Constants.PROP_COMPONENTS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackPathItem(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem)
     */
    @Override
    public void visitCallbackPathItem(Oas30CallbackPathItem node) {
        this.path.prependSegment(node.getPath(), true);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallback(io.apicurio.datamodels.openapi.v3.models.Oas30Callback)
     */
    @Override
    public void visitCallback(Oas30Callback node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_CALLBACKS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkServer(io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer)
     */
    @Override
    public void visitLinkServer(Oas30LinkServer node) {
        this.path.prependSegment(Constants.PROP_SERVER, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition)
     */
    @Override
    public void visitCallbackDefinition(Oas30CallbackDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_CALLBACKS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLink(io.apicurio.datamodels.openapi.v3.models.Oas30Link)
     */
    @Override
    public void visitLink(Oas30Link node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_LINKS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkRequestBodyExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression)
     */
    @Override
    public void visitLinkRequestBodyExpression(Oas30LinkRequestBodyExpression node) {
        this.path.prependSegment(Constants.PROP_REQUEST_BODY, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkParameterExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression)
     */
    @Override
    public void visitLinkParameterExpression(Oas30LinkParameterExpression node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_PARAMETERS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition)
     */
    @Override
    public void visitLinkDefinition(Oas30LinkDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_LINKS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAuthorizationCodeOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30AuthorizationCodeOAuthFlow)
     */
    @Override
    public void visitAuthorizationCodeOAuthFlow(Oas30AuthorizationCodeOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_AUTHORIZATION_CODE, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitClientCredentialsOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30ClientCredentialsOAuthFlow)
     */
    @Override
    public void visitClientCredentialsOAuthFlow(Oas30ClientCredentialsOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_CLIENT_CREDENTIALS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitPasswordOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30PasswordOAuthFlow)
     */
    @Override
    public void visitPasswordOAuthFlow(Oas30PasswordOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_PASSWORD, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitImplicitOAuthFlow(io.apicurio.datamodels.openapi.v3.models.Oas30ImplicitOAuthFlow)
     */
    @Override
    public void visitImplicitOAuthFlow(Oas30ImplicitOAuthFlow node) {
        this.path.prependSegment(Constants.PROP_IMPLICIT, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOAuthFlows(io.apicurio.datamodels.openapi.v3.models.Oas30OAuthFlows)
     */
    @Override
    public void visitOAuthFlows(Oas30OAuthFlows node) {
        this.path.prependSegment(Constants.PROP_FLOWS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitExample(io.apicurio.datamodels.openapi.v3.models.Oas30Example)
     */
    @Override
    public void visitExample(Oas30Example node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_EXAMPLES, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitEncoding(io.apicurio.datamodels.openapi.v3.models.Oas30Encoding)
     */
    @Override
    public void visitEncoding(Oas30Encoding node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_ENCODING, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitMediaType(io.apicurio.datamodels.openapi.v3.models.Oas30MediaType)
     */
    @Override
    public void visitMediaType(Oas30MediaType node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_CONTENT, false);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.visitors.OasNodePathVisitor#visitHeader(io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    public void visitHeader(OasHeader node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_HEADERS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitHeaderDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition)
     */
    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_HEADERS, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBody(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody)
     */
    @Override
    public void visitRequestBody(Oas30RequestBody node) {
        this.path.prependSegment(Constants.PROP_REQUEST_BODY, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBodyDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition)
     */
    @Override
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_REQUEST_BODIES, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitExampleDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition)
     */
    @Override
    public void visitExampleDefinition(Oas30ExampleDefinition node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_EXAMPLES, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitDiscriminator(io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator)
     */
    @Override
    public void visitDiscriminator(Oas30Discriminator node) {
        this.path.prependSegment(Constants.PROP_DISCRIMINATOR, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitNotSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema)
     */
    @Override
    public void visitNotSchema(Oas30NotSchema node) {
        this.path.prependSegment(Constants.PROP_NOT, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOneOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema)
     */
    @Override
    public void visitOneOfSchema(Oas30OneOfSchema node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_ONE_OF);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_ONE_OF, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAnyOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema)
     */
    @Override
    public void visitAnyOfSchema(Oas30AnyOfSchema node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_ANY_OF);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_ANY_OF, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        int idx = NodeCompat.indexOf(node, node.parent(), Constants.PROP_SERVERS);
        if (idx != -1) {
            this.path.prependSegment(String.valueOf(idx), true);
            this.path.prependSegment(Constants.PROP_SERVERS, false);
        }
    }

    /**
     * @see io.apicurio.datamodels.core.visitors.IVisitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        this.path.prependSegment(node.getName(), true);
        this.path.prependSegment(Constants.PROP_VARIABLES, false);
    }
    
}
