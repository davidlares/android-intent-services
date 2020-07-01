# Intent Services with Android

The Android `Intent Services` are made for concurrent long-running tasks, such as file downloading and network requests.

`Services` on Android can run in the main or child Thread, but all of them do not have any direct interaction with the UI, instead, the middleman for this done with Broadcast Messages.

## Service

There are several types of services, the `bound`, `started`, and the `intent`.

1. `Bound` Services: this one is started from an activity or fragment, is called Bound, actually are bound to it. If you shut down the activity, the service is also shut.

2. The `Started` Services: are actually the same as the bound services, but can be shared across many activities, the shutdown behavior is the same

3. The `Intent` Services: this is the wild one, can be triggered when an Intent object is sent, and it runs long enough to accomplish a single task.

## Background Threads

Any software naturally runs code instructions one at a time, these ones are run in sequence. But modern software has the capability to take advantage of every aspect of the hardware specs, especially CPU's. Actually nowadays any computer without multiple CPUs it's pretty much considered old.

So, getting back to the first idea, once a task or instruction is executed, the CPU can't do anything else, this is called a blocking call.

## Multithreading

This term takes place directly for computers with multiple CPUs (Cores) when the CPU it's able to generate multiple Threads that can be executed concurrently. That's the case for modern Android devices, almost all of them have multiple cores in its configuration.

The main Thread is used for UI related activities, this is mandatory, and another operations are handled or should be done with the help of the threading techniques, this is because the any demanding operation can freeze the UI, the same happens with network-related operations.

## Credits

 - [David E Lares](https://twitter.com/davidlares3)

## License

 - [MIT](https://opensource.org/licenses/MIT)
