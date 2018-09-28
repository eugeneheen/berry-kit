package com.github.eugeneheen.berry.kit.security.token;

import java.util.Date;

/**
 * JWT Token抽象实现
 *
 * @author Eugene
 */
public abstract class JwtTokenAbstract implements IJwtToken {
    @Override
    public String getStringProperty(String key) {
        Object obj = getProperty(key);
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override
    public Long getLongProperty(String key) {
        Object obj = getProperty(key);
        if (obj != null) {
            if (obj instanceof Integer) {
                return ((Integer) obj).longValue();
            } else if (obj instanceof Long) {
                return (Long) obj;
            }

            return new Long(obj.toString());
        }
        return null;
    }

    @Override
    public Integer getIntProperty(String key) {
        Object obj = getProperty(key);
        if (obj != null) {
            if (obj instanceof Integer) {
                return (Integer) obj;
            } else if (obj instanceof Long) {
                return ((Long) obj).intValue();
            } else if (obj instanceof Float) {
                return ((Float) obj).intValue();
            } else if (obj instanceof Double) {
                return ((Double) obj).intValue();
            }

            return new Integer(obj.toString());
        }
        return null;
    }

    @Override
    public Float getFloatProperty(String key) {
        Object obj = getProperty(key);
        if (obj != null) {
            if (obj instanceof Integer) {
                return ((Integer) obj).floatValue();
            } else if (obj instanceof Long) {
                return ((Long) obj).floatValue();
            } else if (obj instanceof Float) {
                return (Float) obj;
            } else if (obj instanceof Double) {
                return ((Double) obj).floatValue();
            }

            return new Float(obj.toString());
        }
        return null;
    }

    @Override
    public Double getDoubleProperty(String key) {
        Object obj = getProperty(key);
        if (obj != null) {
            if (obj instanceof Integer) {
                return ((Integer) obj).doubleValue();
            } else if (obj instanceof Long) {
                return ((Long) obj).doubleValue();
            } else if (obj instanceof Float) {
                return ((Float) obj).doubleValue();
            } else if (obj instanceof Double) {
                return (Double) obj;
            }

            return new Double(obj.toString());
        }
        return null;
    }

    @Override
    public Boolean getBooleanProperty(String key) {
        Object obj = getProperty(key);
        if (obj != null) {
            if (obj instanceof Boolean) {
                return (Boolean) obj;
            }

            return new Boolean(obj.toString());
        }
        return null;
    }

    @Override
    public Date getDateProperty(String key) {
        Object obj = getProperty(key);
        if (obj != null) {
            if (obj instanceof Boolean) {
                return (Date) obj;
            }
        }
        return null;
    }
}
