/*
 * blanco Framework
 * Copyright (C) 2004-2009 IGA Tosiki
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
/*******************************************************************************
 * Copyright (c) 2009 IGA Tosiki, NTT DATA BUSINESS BRAINS Corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IGA Tosiki (NTT DATA BUSINESS BRAINS Corp.) - initial API and implementation
 *******************************************************************************/
package blanco.commons.parser;

import org.xml.sax.ContentHandler;

/**
 * �R���e���c�n���h���ɂ��X�g���[����񋟂��܂��B
 * 
 * @author IGA Tosiki
 */
public abstract class ContentHandlerStream implements ContentHandler {
    private ContentHandler contentHandler = null;

    /**
     * �����I�ɋL������K�v�̂���R���e���c�n���h�����L�����܂��B
     */
    private ContentHandlerStream chainedContentHandler = null;

    public ContentHandlerStream() {
    }

    /**
     * �R���e���c�n���h���E�`�F�C�����Ȃ������܂��B
     * 
     * @param arg0
     */
    public ContentHandlerStream(ContentHandlerStream arg0) {
        chainContentHandler(arg0);
    }

    /**
     * �R���e���c�n���h�����Z�b�g���܂��B
     */
    public void setContentHandler(ContentHandler arg0) {
        contentHandler = arg0;
    }

    /**
     * �R���e���c�n���h�����X�g���[���`�F�C���ɐݒ肵�܂��B
     */
    protected void chainContentHandler(ContentHandlerStream arg0) {
        chainedContentHandler = arg0;
    }

    /**
     * �R���e���c�n���h�����擾���܂��B
     */
    public ContentHandler getContentHandler() {
        if (chainedContentHandler == null) {
            return contentHandler;
        } else {
            chainedContentHandler.setContentHandler(contentHandler);
            return chainedContentHandler;
        }
    }
}
