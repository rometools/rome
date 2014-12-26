package com.rometools.modules.thr;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

/**
 * Currently no support for thr:count, thr:updated, thr:total link attributes.
 * 
 * @author <a href="mailto:andreas@feldschmid.com">Andreas Feldschmid</a>
 */
public class ThreadingModuleImpl extends ModuleImpl implements ThreadingModule {

    private static final long serialVersionUID = 1L;

    private String ref;
    private String href;
    private String type;
    private String source;

    public ThreadingModuleImpl() {
        super(ThreadingModule.class, ThreadingModule.URI);
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return ThreadingModule.class;
    }

    @Override
    public void copyFrom(CopyFrom copyFrom) {
        if (copyFrom instanceof ThreadingModule) {
            ThreadingModule module = (ThreadingModule) copyFrom;
            setHref(module.getHref());
            setRef(module.getRef());
            setType(module.getType());
        }
    }

    @Override
    public String getRef() {
        return ref;
    }

    @Override
    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public Object clone() {
        final ThreadingModule m = new ThreadingModuleImpl();
        m.setHref(href);
        m.setRef(ref);
        m.setSource(source);
        m.setType(type);
        return m;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(ThreadingModuleImpl.class, this);
        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(ThreadingModuleImpl.class, this);
        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(ThreadingModuleImpl.class, this);
        return tsBean.toString();
    }

}
