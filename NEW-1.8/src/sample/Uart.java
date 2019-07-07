package sample;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.*;
import java.util.Scanner;

public class Uart {
    private Scanner scanner;
    private OutputStream out = null;
    InputStream in;

    public Uart(String portName){
        connect(portName);
    }

    public void send(String text) {
        if (out != null){
            try {
                out.write(text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void connect(String portName) {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                System.out.println("Connect 1/2");
                CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

                if (commPort instanceof SerialPort) {
                    System.out.println("Connect 2/2");
                    SerialPort serialPort = (SerialPort) commPort;

                    serialPort.setSerialPortParams(38400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                    System.out.println("BaudRate: " + serialPort.getBaudRate());
                    System.out.println("DataBIts: " + serialPort.getDataBits());
                    System.out.println("StopBits: " + serialPort.getStopBits());
                    System.out.println("Parity: " + serialPort.getParity());
                    System.out.println("FlowControl: " + serialPort.getFlowControlMode());

                     in = serialPort.getInputStream();
                    out = serialPort.getOutputStream();

                    scanner = new Scanner(in);
                } else {
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public BufferedReader getReader(){
        if (in!=null)
            return new BufferedReader(new InputStreamReader(in));

        return null;

    }

//    public static void main(String[] args) throws IOException {
//        String string;
//        Uart uart = new Uart("COM7");
//        BufferedReader rd = new BufferedReader(new InputStreamReader(uart.in));
//        while(true){
//
//            if (rd.ready())
//            System.out.println(rd.readLine());
//        }
//    }
}
