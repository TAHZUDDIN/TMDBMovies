//package com.taz.movies.tmdb.tmdbmovies.thread;
//
//import android.os.Looper;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by tahzuddin on 25/06/17.
// */
//
//public class PhotoManager {
//
//
//
//    /*
//     * Gets the number of available cores
//     * (not always the same as the maximum number of cores)
//     */
//    private static int NUMBER_OF_CORES =
//            Runtime.getRuntime().availableProcessors();
//
//
//
//    /**
//     * Constructs the work queues and thread pools used to download
//     * and decode images. Because the constructor is marked private,
//     * it's unavailable to other classes, even in the same package.
//     */
//    private PhotoManager() {
//
//
//        // Sets the amount of time an idle thread waits before terminating
//        private static final int KEEP_ALIVE_TIME = 1;
//        // Sets the Time Unit to seconds
//        private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
//        // Creates a thread pool manager
//        mDecodeThreadPool = new ThreadPoolExecutor(
//                NUMBER_OF_CORES,       // Initial pool size
//                NUMBER_OF_CORES,       // Max pool size
//                KEEP_ALIVE_TIME,
//                KEEP_ALIVE_TIME_UNIT,
//                mDecodeWorkQueue);
//
//        // A queue of Runnables
//         final BlockingQueue<Runnable> mDecodeWorkQueue;
//
//        // Instantiates the queue of Runnables as a LinkedBlockingQueue
//        mDecodeWorkQueue = new LinkedBlockingQueue<Runnable>();
//
//    }
//
//
//
//    // Called by the PhotoView to get a photo
//    static public PhotoTask startDownload(PhotoView imageView, boolean cacheFlag) {
//
//        // Adds a download task to the thread pool for execution
//        sInstance.mDownloadThreadPool.execute(downloadTask.getHTTPDownloadRunnable());
//
//    }
//
//
//
//    // Defines a Handler object that's attached to the UI thread
//    mHandler = new Handler(Looper.getMainLooper()) {
//            /*
//             * handleMessage() defines the operations to perform when
//             * the Handler receives a new Message to process.
//             */
//        @Override
//        public void handleMessage(Message inputMessage) {
//
//            // Gets the image task from the incoming Message object.
//            PhotoTask photoTask = (PhotoTask) inputMessage.obj;
//
//        }
//
//    }
//
//
//
//    public void handleState(PhotoTask photoTask, int state) {
//        switch (state) {
//            // The task finished downloading the image
//            case DOWNLOAD_COMPLETE:
//                // Decodes the image
//                mDecodeThreadPool.execute(
//                        photoTask.getPhotoDecodeRunnable());
//
//
//                // The task finished downloading and decoding the image
//            case TASK_COMPLETE:
//                /*
//                 * Creates a message for the Handler
//                 * with the state and the task object
//                 */
//                Message completeMessage =
//                        mHandler.obtainMessage(state, photoTask);
//                completeMessage.sendToTarget();
//                break;
//
//        }
//
//    }
//
//
//
//
//
//    static  {
//
//        // Creates a single static instance of PhotoManager
//        sInstance = new PhotoManager();
//    }
//}
