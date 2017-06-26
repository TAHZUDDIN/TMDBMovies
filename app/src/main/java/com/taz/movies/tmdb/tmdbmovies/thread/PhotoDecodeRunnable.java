//package com.taz.movies.tmdb.tmdbmovies.thread;
//
//import android.graphics.BitmapFactory;
//
///**
// * Created by tahzuddin on 25/06/17.
// */
//
//public class PhotoDecodeRunnable implements Runnable {
//
//
//
//    PhotoDecodeRunnable(PhotoTask downloadTask) {
//        mPhotoTask = downloadTask;
//    }
//
//
//    @Override
//    public void run() {
//        // Moves the current Thread into the background
//        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
//
//
//
//        // Tries to decode the image buffer
//        returnBitmap = BitmapFactory.decodeByteArray(
//                imageBuffer,
//                0,
//                imageBuffer.length,
//                bitmapOptions
//        );
//        ...
//        // Sets the ImageView Bitmap
//        mPhotoTask.setImage(returnBitmap);
//        // Reports a status of "completed"
//        mPhotoTask.handleDecodeState(DECODE_STATE_COMPLETED);
//
//
//         /*
//         * Stores the current Thread in the PhotoTask instance,
//         * so that the instance
//         * can interrupt the Thread.
//         */
//        mPhotoTask.setImageDecodeThread(Thread.currentThread());
//
//
//        /*
//         * Stores the current Thread in the
//         * object that contains PhotoDecodeRunnable
//         */
//        mPhotoTask.setImageDecodeThread(Thread.currentThread());
//
//
//
//    }
//
//
//
//
//    public static void cancelAll() {
//        /*
//         * Creates an array of Runnables that's the same size as the
//         * thread pool work queue
//         */
//        Runnable[] runnableArray = new Runnable[mDecodeWorkQueue.size()];
//        // Populates the array with the Runnables in the queue
//        mDecodeWorkQueue.toArray(runnableArray);
//        // Stores the array length in order to iterate over the array
//        int len = runnableArray.length;
//        /*
//         * Iterates over the array of Runnables and interrupts each one's Thread.
//         */
//        synchronized (sInstance) {
//            // Iterates over the array of tasks
//            for (int runnableIndex = 0; runnableIndex < len; runnableIndex++) {
//                // Gets the current thread
//                Thread thread = runnableArray[taskArrayIndex].mThread;
//                // if the Thread exists, post an interrupt to it
//                if (null != thread) {
//                    thread.interrupt();
//                }
//            }
//        }
//    }
//
//
//
//
//}
