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
package blanco.commons.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * blanco Framework�ɂ�����XML�֘A�̃��[�e�B���e�B���W�߂��N���X�ł��B
 * 
 * @author IGA Tosiki
 */
public class BlancoXmlUtil {
    /**
     * �I�����ꂽ�m�[�h(�G�������g�ł���)���當������擾���܂��B
     * 
     * ����m�[�h�ɂԂ炳�����Ă���S�Ẵe�L�X�g�f�[�^���擾����ꍇ�ɗ��p���܂��B
     * 
     * @param nodeTarget
     *            �ΏۂƂ���^�[�Q�b�g�m�[�h
     * @return �擾���ꂽ�e�L�X�g������
     */
    public static final String getTextContent(final Node nodeTarget) {
        if (nodeTarget == null) {
            throw new IllegalArgumentException(
                    "�m�[�h����e�L�X�g���擾���郁�\�b�h��null���^�����܂����Bnull�ȊO�̒l��^����悤�ɂ��Ă��������B");
        }

        String result = null;
        final NodeList listText = nodeTarget.getChildNodes();
        final int sizeChildList = listText.getLength();
        for (int indexChild = 0; indexChild < sizeChildList; indexChild++) {
            final Node nodeChild = listText.item(indexChild);
            if (nodeChild instanceof Text) {
                final Text textLook = (Text) nodeChild;
                result = (result == null ? textLook.getData() : result
                        + textLook.getData());
            }
        }
        return result;
    }

    /**
     * �G�������g����w��̃^�O���̕������ǂݍ��݂܂��B
     * 
     * @param elementTarget
     *            �ΏۂƂ���^�[�Q�b�g�G�������g
     * @param tagName
     *            �^�O��
     * @return �擾���ꂽ�e�L�X�g������
     */
    public static final String getTextContent(final Element elementTarget,
            final String tagName) {
        if (elementTarget == null) {
            throw new IllegalArgumentException(
                    "�G�������g����e�L�X�g���擾���郁�\�b�h�ɃG�������g�Ƃ���null���^�����܂����Bnull�ȊO�̒l��^����悤�ɂ��Ă��������B");
        }
        if (tagName == null) {
            throw new IllegalArgumentException(
                    "�G�������g����e�L�X�g���擾���郁�\�b�h�Ƀ^�O���Ƃ���null���^�����܂����Bnull�ȊO�̒l��^����悤�ɂ��Ă��������B");
        }

        String result = null;
        final NodeList listElementTarget = elementTarget
                .getElementsByTagName(tagName);
        final int sizeList = listElementTarget.getLength();
        for (int index = 0; index < sizeList; index++) {
            final Node nodeLook = listElementTarget.item(index);
            if (nodeLook instanceof Element) {
                final Element elementLook = (Element) nodeLook;

                final NodeList listText = elementLook.getChildNodes();
                final int sizeChildList = listText.getLength();
                for (int indexChild = 0; indexChild < sizeChildList; indexChild++) {
                    final Node nodeChild = listText.item(indexChild);
                    if (nodeChild instanceof Text) {
                        final Text textLook = (Text) nodeChild;
                        result = (result == null ? textLook.getData() : result
                                + textLook.getData());
                    }
                }
            }
        }
        return result;
    }

    /**
     * �^����ꂽXML�t�@�C����DOM�c���[�ɕϊ����܂��B
     * 
     * �����I�ɂ́A��ʓI�ɗ��p�����XML�ϊ�API��p���ĕϊ����s���܂��B
     * 
     * @param metaXmlSourceFile
     *            ����XML�t�@�C��
     * @return �o��DOM�c���[
     * @throws IllegalArgumentException
     *             ���̓t�@�C�����s���ł���ꍇ�BXML�ϊ��擾�Ɏ��s�����ꍇ�ȂǁB
     */
    public static final DOMResult transformFile2Dom(final File metaXmlSourceFile) {
        if (metaXmlSourceFile == null) {
            throw new IllegalArgumentException(
                    "�t�@�C������͂Ƃ���DOM�c���[�𐶐����郁�\�b�h��null���^�����܂����Bnull�ȊO�̒l��^����悤�ɂ��Ă��������B");
        }

        if (metaXmlSourceFile.exists() == false) {
            throw new IllegalArgumentException("XML�t�@�C������DOM�ւ̕ϊ�: �w�肳�ꂽ�t�@�C��["
                    + metaXmlSourceFile.getAbsolutePath() + "]��������܂���ł����B");
        }
        if (metaXmlSourceFile.isFile() == false) {
            throw new IllegalArgumentException("XML�t�@�C������DOM�ւ̕ϊ�: �w�肳�ꂽ�t�@�C��["
                    + metaXmlSourceFile.getAbsolutePath()
                    + "]�����ۂɂ̓t�@�C���ł͂���܂���ł����B");
        }
        if (metaXmlSourceFile.canRead() == false) {
            throw new IllegalArgumentException("XML�t�@�C������DOM�ւ̕ϊ�: �w�肳�ꂽ�t�@�C��["
                    + metaXmlSourceFile.getAbsolutePath() + "]���ǂݍ��ݕs�\�ł����B");
        }

        try {
            byte[] fileBytes = BlancoFileUtil.file2Bytes(metaXmlSourceFile);
            return transformStream2Dom(new ByteArrayInputStream(fileBytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "�z�肵�Ȃ���O: XML�ϊ����Ƀt�@�C����������Ȃ���O���������܂����B" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "�z�肵�Ȃ���O: XML�ϊ����Ƀt�@�C�����o�͗�O���������܂����B" + e.toString());
        }
    }

    /**
     * �^����ꂽXML�X�g���[����DOM�c���[�ɕϊ����܂��B
     * 
     * �����I�ɂ́A��ʓI�ɗ��p�����XML�ϊ�API��p���ĕϊ����s���܂��B
     * 
     * @param inXmlSource
     *            ����XML�X�g���[��
     * @return �o��DOM�c���[
     * @throws IllegalArgumentException
     *             ���̓t�@�C�����s���ł���ꍇ�BXML�ϊ��擾�Ɏ��s�����ꍇ�ȂǁB
     */
    public static final DOMResult transformStream2Dom(
            final InputStream inXmlSource) {
        if (inXmlSource == null) {
            throw new IllegalArgumentException(
                    "����XML�X�g���[����DOM�c���[�ɕϊ����鏈���ɁA�X�g���[���Ƃ���null���n����܂����B�X�g���[���ɂ�null�ȊO��^���Ă��������B");
        }

        try {
            final DOMResult result = new DOMResult();
            final TransformerFactory tf = TransformerFactory.newInstance();
            final Transformer transformer = tf.newTransformer();
            transformer.transform(new StreamSource(inXmlSource), result);
            return result;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "�z�肵�Ȃ���O: XML�ϊ��R���t�B�O���[�V������O���������܂����B" + e.toString());
        } catch (TransformerException e) {
            throw new IllegalArgumentException("�z�肵�Ȃ���O: XML�ϊ���O���������܂����B"
                    + e.toString());
        }
    }

    /**
     * �^����ꂽDOM�c���[��XML�t�@�C���ɕϊ����܂��B
     * 
     * �����I�ɂ́A��ʓI�ɗ��p�����XML�ϊ�API��p���ĕϊ����s���܂��B
     * 
     * @param document
     *            XML�h�L�������g
     * @param metaXmlResultFile
     *            �o��XML�t�@�C��
     * @throws IllegalArgumentException
     *             ����XML�c���[���s���ł���ꍇ�BXML�ϊ��擾�Ɏ��s�����ꍇ�ȂǁB
     */
    public static final void transformDom2File(final Document document,
            final File metaXmlResultFile) {
        if (document == null) {
            throw new IllegalArgumentException(
                    "DOM����XML�t�@�C���ւ̕ϊ�: ����XML�h�L�������g��null���^�����܂������A���̃p�����[�^�ɂ�null�ȊO�̒l���w�肷��K�v������܂��B");
        }
        if (metaXmlResultFile == null) {
            throw new IllegalArgumentException(
                    "DOM����XML�t�@�C���ւ̕ϊ�: �o��XML�t�@�C����null���^�����܂������A���̃p�����[�^�ɂ�null�ȊO�̒l���w�肷��K�v������܂��B");
        }

        if (metaXmlResultFile.exists() == true
                && metaXmlResultFile.canWrite() == false) {
            throw new IllegalArgumentException("DOM����XML�t�@�C���ւ̕ϊ�: �w�肳�ꂽ�t�@�C��["
                    + metaXmlResultFile.getAbsolutePath() + "]�͏������݂ł��܂���ł����B");
        }

        try {
            final OutputStream outStream = new BufferedOutputStream(
                    new FileOutputStream(metaXmlResultFile));
            try {
                transformDom2Stream(document, outStream);
                // ����I�������ꍇ�ɂ̓t���b�V�����s���܂��B
                outStream.flush();
            } finally {
                outStream.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("DOM����XML�t�@�C���ւ̕ϊ�: �w�肳�ꂽ�t�@�C��["
                    + metaXmlResultFile.getAbsolutePath()
                    + "]�ւ̕ϊ��̉ߒ��œ��o�͗�O���������܂����B" + e.toString());
        }
    }

    /**
     * �^����ꂽDOM�c���[��XML�X�g���[���ɕϊ����܂��B
     * 
     * �����I�ɂ́A��ʓI�ɗ��p�����XML�ϊ�API��p���ĕϊ����s���܂��B
     * 
     * @param document
     *            ����XML�c���[
     * @param outXmlResult
     *            �o��DOM�X�g���[��
     * @throws IllegalArgumentException
     *             �p�����[�^���s���ł���ꍇ�BXML�ϊ��擾�Ɏ��s�����ꍇ�ȂǁB
     */
    public static final void transformDom2Stream(final Document document,
            final OutputStream outXmlResult) {
        if (document == null) {
            throw new IllegalArgumentException(
                    "DOM�c���[���o��XML�X�g���[���ɕϊ����鏈���ɁAXML�h�L�������g�Ƃ���null���n����܂����BXML�h�L�������g�ɂ�null�ȊO��^���Ă��������B");
        }
        if (outXmlResult == null) {
            throw new IllegalArgumentException(
                    "DOM�c���[���o��XML�X�g���[���ɕϊ����鏈���ɁA�X�g���[���Ƃ���null���n����܂����B�X�g���[���ɂ�null�ȊO��^���Ă��������B");
        }

        try {
            final DOMSource source = new DOMSource(document);
            final TransformerFactory tf = TransformerFactory.newInstance();
            final Transformer transformer = tf.newTransformer();
            transformer.transform(source, new StreamResult(outXmlResult));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "�z�肵�Ȃ���O: XML�ϊ��R���t�B�O���[�V������O���������܂����B" + e.toString());
        } catch (TransformerException e) {
            throw new IllegalArgumentException("�z�肵�Ȃ���O: XML�ϊ���O���������܂����B"
                    + e.toString());
        }
    }

    /**
     * XML�h�L�������g��V�K�쐬���܂��B
     * 
     * �h�L�������g�t�@�N�g������h�L�������g�r���_�[���擾���A���ꂩ��XML�h�L�������g��V�K�쐬���܂��B
     * 
     * @return �V�K�ɍ쐬���ꂽXML�h�L�������g�E�I�u�W�F�N�g
     * @throws IllegalArgumentException
     *             �h�L�������g�r���_�[�̐V�K�쐬�Ɏ��s�����ꍇ�B
     */
    public static final Document newDocument() {
        final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new IllegalArgumentException(
                    "�h�L�������g�r���_�[(DocumentBuilder)�̐V�K�����Ɏ��s���܂����B" + e.toString());
        }
        return builder.newDocument();
    }

    /**
     * �w��̃G�������g�Ɏw��̃G�������g���ɂ��q�G�������g��ǉ����܂��B
     * 
     * �e�L�X�g�̎w�肪����΃e�L�X�g�m�[�h��ǉ����܂��B�e�L�X�g��null���w�肵���ꍇ�ɂ͎q�G�������g�̒ǉ��݂̂��s���܂��B
     * 
     * @param document
     *            XML�h�L�������g
     * @param elementParent
     *            �e�G�������g
     * @param addElementName
     *            �V�G�������g�̖��O
     * @param addElementText
     *            �V�G�������g�ɒǉ�����e�L�X�g�Bnull���w�肵���ꍇ�ɂ̓e�L�X�g�ǉ��͏ȗ������B
     */
    public static final void addChildElement(final Document document,
            final Element elementParent, final String addElementName,
            final String addElementText) {
        if (document == null) {
            throw new IllegalArgumentException(
                    "�G�������g�ǉ� (addElementText) �ɁA�h�L�������g�Ƃ���null���n����܂����Bnull�ȊO�̒l���w�肵�Ă��������B");
        }
        if (elementParent == null) {
            throw new IllegalArgumentException(
                    "�G�������g�ǉ� (addElementText) �ɁA�e�G�������g�Ƃ���null���n����܂����Bnull�ȊO�̒l���w�肵�Ă��������B");
        }
        if (addElementName == null) {
            throw new IllegalArgumentException(
                    "�G�������g�ǉ� (addElementText) �ɁA�V�G�������g�̖��O�Ƃ���null���n����܂����Bnull�ȊO�̒l���w�肵�Ă��������B");
        }
        if (addElementName.length() == 0) {
            throw new IllegalArgumentException(
                    "�G�������g�ǉ� (addElementText) �ɁA�V�G�������g�̖��O�Ƃ��Ē���0�̕����񂪓n����܂����B1�ȏ�̒����̒l���w�肵�Ă��������B");
        }

        // �ŏ��ɒǉ��������G�������g�𐶐����܂��B
        final Element elementAdd = document.createElement(addElementName);
        if (addElementText != null) {
            // �e�L�X�g�̎w�肪����ꍇ�ɂ̂ݒǉ��������s���܂��B
            // �e�L�X�g�m�[�h�𐶐����܂��B
            final Text elemnetTextNode = document
                    .createTextNode(addElementText);
            // �e�L�X�g�m�[�h���G�������g�ɒǉ����܂��B
            elementAdd.appendChild(elemnetTextNode);
        }
        // �Ō�ɐe�G�������g�ɐV�G�������g��ǉ����܂��B
        elementParent.appendChild(elementAdd);
    }

    /**
     * �w��̃G�������g���̃G�������g���������܂��B
     * 
     * @param path
     *            �p�X aaa/bbb �̂悤�Ɏw�肵�܂��B
     * @return �擾���ꂽ�G�������g
     */
    public static final Element getElement(final Node nodeTarget,
            final String path) {
        if (nodeTarget == null) {
            throw new IllegalArgumentException(
                    "�w��̃G�������g������G�������g���������郁�\�b�h�̃m�[�h��null���^�����܂����Bnull�ȊO�̒l��^����悤�ɂ��Ă��������B");
        }
        if (nodeTarget == null) {
            throw new IllegalArgumentException(
                    "�w��̃G�������g������G�������g���������郁�\�b�h�̃p�X����null���^�����܂����Bnull�ȊO�̒l��^����悤�ɂ��Ă��������B");
        }

        final String[] splitPath = BlancoNameUtil.splitPath(path);

        final NodeList nodeList = nodeTarget.getChildNodes();
        final int nodeLength = nodeList.getLength();
        for (int index = 0; index < nodeLength; index++) {
            final Node nodeLook = nodeList.item(index);
            if (nodeLook instanceof Element) {
                final Element elementLook = (Element) nodeLook;
                if (elementLook.getNodeName().equals(splitPath[0])) {
                    // �q�b�g���܂����B
                    if (splitPath.length == 1) {
                        // System.out.println("�������܂����B" +
                        // elementLook.getNodeName());
                        return elementLook;
                    } else {
                        // System.out.println("�ċA���s���܂��B" +
                        // elementLook.getNodeName());
                        return getElement(elementLook, path
                                .substring(splitPath[0].length() + 1));
                    }
                }
            }
        }
        // �����ł��܂���ł����B
        return null;
    }
}
