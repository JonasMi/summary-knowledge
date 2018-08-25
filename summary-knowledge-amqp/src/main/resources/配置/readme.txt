环境变量指定配置文件的路径:
RABBITMQ_CONFIG_FILE
RABBITMQ_ADVANCED_CONFIG_FILE
In most distributions we place this example file in the same location as the real file should be placed (see above). 
However, for the Debian and RPM distributions policy forbids doing so; 
instead you can find it in /usr/share/doc/rabbitmq-server/ or /usr/share/doc/rabbitmq-server-3.7.7/ respectively.

在UNIX系统上，cookie通常位于  /var/lib/rabbitmq/.erlang.cookie

Nodes hosting RabbitMQ should have at least 128MB of memory available at all times.
The recommended vm_memory_high_watermark range is 0.40 to 0.66
Values above 0.7 are not recommended. The OS and file system must be left with at least 30% of the memory, 
otherwise performance may degrade severely due to paging.

{disk_free_limit, {mem_relative, 1.0}} is the minimum recommended value and it translates to the total amount of memory available. For example, on a host dedicated to RabbitMQ with 4GB of system memory, if available disk space drops below 4GB, all publishers will be blocked and no new messages will be accepted. Queues will need to be drained, normally by consumers, before publishing will be allowed to resume.
{disk_free_limit, {mem_relative, 1.5}} is a safer production value. On a RabbitMQ node with 4GB of memory, if available disk space drops below 6GB, all new messages will be blocked until the disk alarm clears. If RabbitMQ needs to flush to disk 4GB worth of data, as can sometimes be the case during shutdown, there will be sufficient disk space available for RabbitMQ to start again. In this specific example, RabbitMQ will start and immediately block all publishers since 2GB is well under the required 6GB.
{disk_free_limit, {mem_relative, 2.0}} is the most conservative production value, we cannot think of any reason to use anything higher.If you want full confidence in RabbitMQ having all the disk space that it needs, at all times, this is the value to use.