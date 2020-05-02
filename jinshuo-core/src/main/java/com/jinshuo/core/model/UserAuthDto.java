package com.jinshuo.core.model;

/**
 * Created by Administrator on 2020/4/11.
 */

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAuthDto implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String nickname;
    private String password;
    private boolean enabled;
    private Integer status;
    private Long shopId;
    private Long merchantId;
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuthDto() {
    }

    public UserAuthDto(String id, String username, String password, Long merchantId, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.merchantId = merchantId;
        this.enabled = enabled;
    }

    public UserAuthDto(String id, String username, String nickname, String password, Long shopId, boolean enabled) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.shopId = shopId;
        this.enabled = enabled;
    }

    public UserAuthDto(String id, String username, String password, boolean enabled, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.status = status;
    }

    public UserAuthDto(String id, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getId() {
        return this.id;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auths;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String toString() {
        return "MyUserDetails [id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", enabled=" + this.enabled + ", authorities=" + this.authorities + "]";
    }

    public String getNickname() {
        return this.nickname;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Long getShopId() {
        return this.shopId;
    }

    public Long getMerchantId() {
        return this.merchantId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof UserAuthDto)) {
            return false;
        } else {
            UserAuthDto other = (UserAuthDto)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label111: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if(this$id == null) {
                        if(other$id == null) {
                            break label111;
                        }
                    } else if(this$id.equals(other$id)) {
                        break label111;
                    }

                    return false;
                }

                Object this$username = this.getUsername();
                Object other$username = other.getUsername();
                if(this$username == null) {
                    if(other$username != null) {
                        return false;
                    }
                } else if(!this$username.equals(other$username)) {
                    return false;
                }

                Object this$nickname = this.getNickname();
                Object other$nickname = other.getNickname();
                if(this$nickname == null) {
                    if(other$nickname != null) {
                        return false;
                    }
                } else if(!this$nickname.equals(other$nickname)) {
                    return false;
                }

                label90: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if(this$password == null) {
                        if(other$password == null) {
                            break label90;
                        }
                    } else if(this$password.equals(other$password)) {
                        break label90;
                    }

                    return false;
                }

                if(this.isEnabled() != other.isEnabled()) {
                    return false;
                } else {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if(this$status == null) {
                        if(other$status != null) {
                            return false;
                        }
                    } else if(!this$status.equals(other$status)) {
                        return false;
                    }

                    label75: {
                        Object this$shopId = this.getShopId();
                        Object other$shopId = other.getShopId();
                        if(this$shopId == null) {
                            if(other$shopId == null) {
                                break label75;
                            }
                        } else if(this$shopId.equals(other$shopId)) {
                            break label75;
                        }

                        return false;
                    }

                    Object this$merchantId = this.getMerchantId();
                    Object other$merchantId = other.getMerchantId();
                    if(this$merchantId == null) {
                        if(other$merchantId != null) {
                            return false;
                        }
                    } else if(!this$merchantId.equals(other$merchantId)) {
                        return false;
                    }

                    Object this$authorities = this.getAuthorities();
                    Object other$authorities = other.getAuthorities();
                    if(this$authorities == null) {
                        if(other$authorities != null) {
                            return false;
                        }
                    } else if(!this$authorities.equals(other$authorities)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserAuthDto;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null?43:$id.hashCode());
        Object $username = this.getUsername();
        result = result * 59 + ($username == null?43:$username.hashCode());
        Object $nickname = this.getNickname();
        result = result * 59 + ($nickname == null?43:$nickname.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null?43:$password.hashCode());
        result = result * 59 + (this.isEnabled()?79:97);
        Object $status = this.getStatus();
        result = result * 59 + ($status == null?43:$status.hashCode());
        Object $shopId = this.getShopId();
        result = result * 59 + ($shopId == null?43:$shopId.hashCode());
        Object $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null?43:$merchantId.hashCode());
        Object $authorities = this.getAuthorities();
        result = result * 59 + ($authorities == null?43:$authorities.hashCode());
        return result;
    }
}
