package com.mockitolimits.service;

/**
 * @author sei3
 * on 20/11/2018.
 */
public class NodeServiceFcty {
    private static NodeServiceFcty instance = new NodeServiceFcty();

    public static NodeServiceFcty getInstance() {
        return instance;
    }

    private NodeServiceFcty() {
    }

    private final NodeService nodeService = new NodeServiceImpl();

    public NodeService getNodeService() {
        return nodeService;
    }


    private final static NodeService STATIC_NODE_SERVICE = new NodeServiceImpl();

    public NodeService getStaticNodeService() {
        return STATIC_NODE_SERVICE;
    }

}
