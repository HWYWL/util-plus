package com.github.hwywl.config;

/**
 * 日志级别
 *
 * @author YI
 * @date 2020/4/3 16:32
 */
public class Loglevel {
    /**
     * 很低的日志级别，一般不会使用。
     */
    public static final String TRACE = "trace";
    /**
     * 指出细粒度信息事件对调试应用程序是非常有帮助的，主要用于开发过程中打印一些运行信息。
     */
    public static final String DEBUG = "debug";
    /**
     * 消息在粗粒度级别上突出强调应用程序的运行过程。
     */
    public static final String INFO = "info";
    /**
     * 表明会出现潜在错误的情形，有些信息不是错误信息，但是也要给程序员的一些提示。
     */
    public static final String WARN = "warn";
    /**
     * 指出虽然发生错误事件，但仍然不影响系统的继续运行。
     */
    public static final String ERROR = "error";
    /**
     * 指出每个严重的错误事件将会导致应用程序的退出。
     */
    public static final String FATAL = "fatal";
}