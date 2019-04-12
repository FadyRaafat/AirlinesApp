package com.example.airlinesapp.Utils;

import android.os.Handler;

public class ThreadUtils {

    public static RunnableExecutor run(final Runnable r) {
        return new RunnableExecutor(r);
    }

    public static class RunnableExecutor {
        private final Handler handler;
        private long every, delay;
        private Runnable runnable;

        RunnableExecutor(Runnable r) {
            runnable = r;
            handler = new Handler();
        }

        public RunnableExecutor every(long timeMillis) {
            every = timeMillis;
            return this;
        }

        public RunnableExecutor after(long timeMillis) {
            delay = timeMillis;
            return this;
        }

        public Handler exec() {
            if (every > 0) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runnable.run();
                        handler.postDelayed(this, every);
                    }
                }, delay);
            } else handler.postDelayed(runnable, delay);
            return handler;
        }
    }
}
