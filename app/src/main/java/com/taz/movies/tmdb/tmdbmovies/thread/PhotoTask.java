//package com.taz.movies.tmdb.tmdbmovies.thread;
//
///**
// * Created by tahzuddin on 25/06/17.
// */
//
//public class PhotoTask {
//
//
//
//    // Gets a handle to the object that creates the thread pools
//    sPhotoManager = PhotoManager.getInstance();
//    ...
//    public void handleDecodeState(int state) {
//        int outState;
//        // Converts the decode state to the overall state.
//        switch(state) {
//            case PhotoDecodeRunnable.DECODE_STATE_COMPLETED:
//                outState = PhotoManager.TASK_COMPLETE;
//                break;
//
//        }
//
//        // Calls the generalized state method
//        handleState(outState);
//    }
//
//    // Passes the state to PhotoManager
//    void handleState(int state) {
//        /*
//         * Passes a handle to this task and the
//         * current state to the class that created
//         * the thread pools
//         */
//        sPhotoManager.handleState(this, state);
//    }
//
//
//
//}
