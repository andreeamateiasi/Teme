import java.util.Queue;

class Consumer extends Thread {
	private Queue<Integer> queue;
	private int maxSize;

	public Consumer(Queue<Integer> queue, int maxSize) {
		this.queue = queue;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
					System.out.println("Queue is empty," + "Consumer thread is waiting"
							+ " for producer thread to put something in queue");
					try {
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				System.out.println("Consuming value : " + queue.remove());
				queue.notifyAll();
			}
		}
	}
}