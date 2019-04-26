# Processes

## Creating a process

Each process is started by another process.
A process has a process id (PID) and it also knows its parent's process id.

When a process is creating a child process, it can assign the following values:
* path to the program's binary (file with runnable code)
* command line arguments
* environment variables
* working directory
* standard input/output/error streams

### Path to the binary

The path to the binary can be either an absolute path or just the binary's filename.
The most foolproof way is to use an absolute path, e.g. "/usr/bin/java" or "C:\jdk-9.0.4\bin\java.exe".

When just the binary's filename is provided, then the absolute path must be looked up.
To do that, each location in the "PATH" environment variable is searched for the named binary and the first match is used.
This search usually happens automatically.

To start a process in Java:
```
// looks up the program "netstat" from the PATH
new ProcessBuilder("netstat").start();
```

### Program arguments

The program can be passed 0..N arguments.
In case of Java programs, these will be visible in the `String[]` parameter of the main method.

For example, starting the command `cd my folder` would start the program called "cd" with the arguments `["my", "folder"]`.
The command `cd "my folder"` would start "cd" with the arguments `["my folder"]`.

To start a process in Java:
```
List<String> command1 = Arrays.asList("cd", "my", "folder");
new ProcessBuilder(command1).start(); // runs cd with 2 arguments

List<String> command2 = Arrays.asList("cd", "my folder");
new ProcessBuilder(command2).start(); // runs cd with 1 argument
```

#### Arguments to the java runtime
A java program is started with the command line `java [options] main-class [args]`.
The command line arguments passed to java **before** the main class are interpreted by the java runtime (e.g. classpath and memory arguments).
Only the arguments **after** the main class are passed to the main method of the main class.

### Environment variables

Environment variables are similar to the program arguments, but by default they are inherited from the parent process (unlike program arguments which must be specified explicitly).

Common environment variables are "PATH" (used to look up programs by name), "HOME" (the home directory of the user) etc.
Additional variables (e.g. JAVA_HOME) can be specified in the system configuration.
The variables can also be specified by the parent process when creating a child.

To start a process in Java with modified environment variables:
```
ProcessBuilder builder = new ProcessBuilder("mvn");
builder.environment().put("JAVA_HOME", "/path/to/javahome");
builder.start();
```

### Working directory

The current working directory is used when resolving relative file paths.
By default, the child process inherits the working directory of the parent.
The parent can specify a different working directory when creating a child.
Each process can also change its working directory at any moment.

For example, when the program is started in the directory "/home/user", that directory will become its working directory.
When the program tries to look up the file using the relative path "docs/notes.txt", then the result will be "/home/user/docs/notes.txt".

To start a process in Java in a specified working directory:
```
ProcessBuilder builder = new ProcessBuilder("ls"); // or "dir" in windows
builder.directory(new File("/home/user"));
builder.start();
```

### Standard streams

Each process has three streams when it is created:
* standard output (stdout, or`System.out` in java)
* standard error (stderr, or`System.err` in java)
* standard input (stdin, or`System.in` in java)

Usually these streams are inherited from the parent process.
In this case, when a process is writing output to a terminal window and starts a child process, then the child process's stdout will also go to parent's terminal window.

Using inherited streams:
```
ProcessBuilder builder = new ProcessBuilder("myprogram");
builder.inheritIO();
builder.start();
```

Alternatively, the standard streams can be redirected.
To redirect the stdout of a child, the parent process will create a "pipe".
A pipe is a one-way stream - one end of the pipe is for writing and the other end of the pipe is for reading.
The parent will give the writing end of the pipe to the child, which will become the child's stdout.
When the child writes something to its stdout, then the parent can read it from its reading end of the pipe.

Using piped streams:
```
Process proc = new ProcessBuilder("myprogram").start();

// child can read this from its stdin
OutputStream out = proc.getOutputStream();
out.write("hello child".getBytes());
out.flush();

// the child can write to us using its stdout
Scanner in = new Scanner(proc.getInputStream());
String response = in.nextLine();
```

Note that the parent and the child don't have to send text to each other - the pipe can transport any binary data.
For example, the child could start streaming audio to its stdout and the parent could read it.

## Program lifecycle

Once the program is started, it will run in parallel to the parent process.
When the child finishes (whenever that is), it will return an exit value.
A exit value of zero means that the child process has finished successfully and terminated.
Any other value usually means that some problem was encountered.
The exact meaning of the exit value is defined by the process itself.

Waiting a process to finish and reading its exit value:
```
Process proc = new ProcessBuilder("myprogram").start();
int value = proc.waitFor();
```

A process can exit at any time with any exit value:
```
public static void main(String[] args) throws Exception {
  boolean success = Math.random() > 0.5;
  if (success) {
    System.exit(0);
  } else {
    System.exit(1);
  }
}
```

When the Java main method reaches the end without throwing an exception, then the process will automatically exit with zero.

It is also possible to kill another process:
```
Process proc = new ProcessBuilder("myprogram").start();
proc.destroy();
int value = proc.waitFor();
```

## Task

There are 5 tasks in the repository for practicing working with child processes.
