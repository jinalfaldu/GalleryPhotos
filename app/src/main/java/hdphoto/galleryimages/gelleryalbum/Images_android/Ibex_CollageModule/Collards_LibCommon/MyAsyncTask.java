package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Collards_LibCommon;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class MyAsyncTask<Params, Progress, Result> {
    static MyAsyncTaskResult result;
    static int[] switchTableArray;
    FutureTask<Result> mFuture;
    WorkerRunnable<Params, Result> mWorker;
    Object obj;
    final String LOG_TAG = "MyAsyncTask";
    InternalHandler sHandler = new InternalHandler();
    ThreadFactory sThreadFactory = new ThreadFactory() {
        AtomicInteger mCount;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            this.mCount = new AtomicInteger(1);
            return new Thread(runnable, "MyAsyncTask #" + this.mCount.getAndIncrement());
        }
    };
    BlockingQueue<Runnable> sWorkQueue = new LinkedBlockingQueue(10);
    ThreadPoolExecutor sExecutor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, this.sWorkQueue, this.sThreadFactory);
    volatile Status mStatus = Status.PENDING;


    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    public abstract Result doInBackground(Params... paramsArr);

    public void onCancelled() {
    }

    public void onPostExecute(Result result2) {
    }

    public void onPreExecute() {
    }

    public void onProgressUpdate(Progress... progressArr) {
    }


    public static class InternalHandler extends Handler {
        private InternalHandler() {
        }

        @Override
        public void handleMessage(Message message) {
            MyAsyncTask.result = (MyAsyncTaskResult) message.obj;
            if (message.what == 1) {
                MyAsyncTask.result.mTask.finish(MyAsyncTask.result.mData[0]);
            } else if (message.what != 2) {
                if (message.what != 3) {
                    return;
                }
                MyAsyncTask.result.mTask.onCancelled();
            }
            MyAsyncTask.result.mTask.onProgressUpdate(MyAsyncTask.result.mData);
            MyAsyncTask.result.mTask.onCancelled();
        }
    }


    public static class MyAsyncTaskResult<Data> {
        final Data[] mData;
        final MyAsyncTask mTask;

        MyAsyncTaskResult(MyAsyncTask myAsyncTask, Data... dataArr) {
            this.mTask = myAsyncTask;
            this.mData = dataArr;
        }
    }


    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;

        private WorkerRunnable() {
        }
    }

    static int[] switchTableIntegerArray() {
        int[] iArr = switchTableArray;
        if (iArr == null) {
            iArr = new int[Status.values().length];
            iArr[Status.FINISHED.ordinal()] = 3;
            iArr[Status.PENDING.ordinal()] = 1;
            try {
                iArr[Status.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused) {
            }
            switchTableArray = iArr;
        }
        return iArr;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final boolean isCancelled() {
        return this.mFuture.isCancelled();
    }

    public final boolean cancel(boolean z) {
        return this.mFuture.cancel(z);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }

    public final Result get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(j, timeUnit);
    }

    public final MyAsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        if (this.mStatus != Status.PENDING) {
            int i = switchTableIntegerArray()[this.mStatus.ordinal()];
            if (i == 2) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i == 3) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.mStatus = Status.RUNNING;
        onPreExecute();
        WorkerRunnable<Params, Result> workerRunnable = new WorkerRunnable<Params, Result>() {
            @Override
            public Result call() throws Exception {
                Process.setThreadPriority(10);
                return (Result) MyAsyncTask.this.doInBackground(this.mParams);
            }
        };
        this.mWorker = workerRunnable;
        workerRunnable.mParams = paramsArr;
        FutureTask<Result> futureTask = new FutureTask<Result>(this.mWorker) {
            @Override
            public void done() {
                try {
                    MyAsyncTask.this.obj = get();
                } catch (InterruptedException e) {
                    Log.w("MyAsyncTask", e);
                } catch (CancellationException unused) {
                    MyAsyncTask.this.sHandler.obtainMessage(3, new MyAsyncTaskResult(MyAsyncTask.this, null)).sendToTarget();
                    return;
                } catch (ExecutionException e2) {
                    throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
                } catch (Throwable th) {
                    new RuntimeException("An error occured while executing doInBackground()", th);
                }
                InternalHandler internalHandler = MyAsyncTask.this.sHandler;
                MyAsyncTask myAsyncTask = MyAsyncTask.this;
                internalHandler.obtainMessage(1, new MyAsyncTaskResult(myAsyncTask, myAsyncTask.obj)).sendToTarget();
            }
        };
        this.mFuture = futureTask;
        this.sExecutor.execute(futureTask);
        return this;
    }

    public final void publishProgress(Progress... progressArr) {
        this.sHandler.obtainMessage(2, new MyAsyncTaskResult(this, progressArr)).sendToTarget();
    }

    public void finish(Result result2) {
        onPostExecute(result2);
        this.mStatus = Status.FINISHED;
    }
}
