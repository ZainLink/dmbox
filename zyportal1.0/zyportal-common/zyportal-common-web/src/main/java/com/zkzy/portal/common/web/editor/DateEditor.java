package com.zkzy.portal.common.web.editor;

import com.zkzy.portal.common.utils.DateHelper;

import java.beans.PropertyEditorSupport;

/**
 * @author admin
 */
public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        setValue(DateHelper.parseDate(text));
    }

}
