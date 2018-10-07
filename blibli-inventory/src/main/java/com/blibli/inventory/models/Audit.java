//package com.blibli.inventory.models;
//
//import java.util.Date;
//
//public abstract class Audit {
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created", nullable = false)
//    private Date created;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated", nullable = false)
//    private Date updated;
//
//    @PrePersist
//    protected void onCreate() {
//        updated = created = new Date();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updated = new Date();
//    }
//}
