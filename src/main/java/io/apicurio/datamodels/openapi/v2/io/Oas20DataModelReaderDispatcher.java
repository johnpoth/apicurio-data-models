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

package io.apicurio.datamodels.openapi.v2.io;

import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.openapi.io.OasDataModelReaderDispatcher;
import io.apicurio.datamodels.openapi.v2.models.Oas20Definitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Example;
import io.apicurio.datamodels.openapi.v2.models.Oas20Headers;
import io.apicurio.datamodels.openapi.v2.models.Oas20Items;
import io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions;
import io.apicurio.datamodels.openapi.v2.models.Oas20Scopes;
import io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions;
import io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor;

/**
 * Implements an OpenAPI 2.0 version of a data model reader dispatcher.
 * @author eric.wittmann@gmail.com
 */
public class Oas20DataModelReaderDispatcher extends OasDataModelReaderDispatcher implements IOas20Visitor {

    /**
     * Constructor.
     * @param json
     * @param reader
     */
    public Oas20DataModelReaderDispatcher(Object json, Oas20DataModelReader reader) {
        super(json, reader);
    }

    /**
     * Casts the reader.
     */
    private Oas20DataModelReader oas20Reader() {
        return (Oas20DataModelReader) this.reader;
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitItems(io.apicurio.datamodels.openapi.v2.models.Oas20Items)
     */
    @Override
    public void visitItems(Oas20Items node) {
        this.oas20Reader().readItems(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitScopes(io.apicurio.datamodels.openapi.v2.models.Oas20Scopes)
     */
    @Override
    public void visitScopes(Oas20Scopes node) {
        this.oas20Reader().readScopes(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitSecurityDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20SecurityDefinitions)
     */
    @Override
    public void visitSecurityDefinitions(Oas20SecurityDefinitions node) {
        this.oas20Reader().readSecurityDefinitions(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20Definitions)
     */
    @Override
    public void visitDefinitions(Oas20Definitions node) {
        this.oas20Reader().readDefinitions(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitParameterDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ParameterDefinitions)
     */
    @Override
    public void visitParameterDefinitions(Oas20ParameterDefinitions node) {
        this.oas20Reader().readParameterDefinitions(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitExample(io.apicurio.datamodels.core.models.common.IExample)
     */
    @Override
    public void visitExample(IExample node) {
        this.oas20Reader().readExample(this.json, (Oas20Example) node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitHeaders(io.apicurio.datamodels.openapi.v2.models.Oas20Headers)
     */
    @Override
    public void visitHeaders(Oas20Headers node) {
        this.oas20Reader().readHeaders(this.json, node);
        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v2.visitors.IOas20Visitor#visitResponseDefinitions(io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinitions)
     */
    @Override
    public void visitResponseDefinitions(Oas20ResponseDefinitions node) {
        this.oas20Reader().readResponseDefinitions(this.json, node);
        
    }

}
