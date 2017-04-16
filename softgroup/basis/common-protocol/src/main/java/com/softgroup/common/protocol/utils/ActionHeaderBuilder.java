package com.softgroup.common.protocol.utils;

import com.softgroup.common.protocol.ActionHeader;


/**
 * Created by anton on 06.04.17.
 */
public class ActionHeaderBuilder {

    private String uuid;
    private String originUuid;
    private String command;
    private String type;
    private String version;

    public ActionHeaderBuilder withUuid(String uuid){
        this.uuid = uuid;
        return this;
    }

    public ActionHeaderBuilder withOriginUuid(String originUuid){
        this.originUuid = originUuid;
        return this;
    }

    public ActionHeaderBuilder withCommand(String command){
        this.command = command;
        return this;
    }

    public ActionHeaderBuilder withType(String type){
        this.type = type;
        return this;
    }

    public ActionHeaderBuilder withVersion(String version){
        this.version = version;
        return this;
    }

    public ResponseBuilder build(ResponseBuilder requestBuilder){
        return requestBuilder.withActionHeader(new ActionHeader(this));
    }

    public ActionHeader build(){
        return new ActionHeader(this);
    }

    public String getUuid() {
        return uuid;
    }

    public String getOriginUuid() {
        return originUuid;
    }

    public String getCommand() {
        return command;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }
}
