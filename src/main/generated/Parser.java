package generated;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

abstract class Symbol {
    public String name;

    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}

class LEFT extends Symbol{
}
class RIGHT extends Symbol{
}
class MULTIPLY extends Symbol{
}
class START extends Symbol{
}
class ID extends Symbol{
}
class ASSIGN extends Symbol{
}

/**
 * @author Dokyme
 */
public class Parser {
    private static final String TOKEN_SPLITER = ",";
    private static final int TOKEN_TOKS_NUMBER = 3;

    private Stack<Integer> stateStack;
    private Stack<Symbol> symbolStack;
    private String outputPath;
    private String inputPath;
    private BufferedWriter outputWriter;
    private BufferedReader inputReader;
    private boolean debug = false;

    private String raw;
    private int innerCode;
    private Symbol token;
    private List<Symbol> reduce;

	

    public Parser() {
        stateStack = new Stack<>();
        symbolStack = new Stack<>();
    }

    private void run() {
        try {
            while ((token = readToken()) != null) {
				switch(stateStack.peek()) {
					case 0:
						state_0();
						break;
					case 1:
						state_1();
						break;
					case 2:
						state_2();
						break;
					case 3:
						state_3();
						break;
					case 4:
						state_4();
						break;
					case 5:
						state_5();
						break;
					case 6:
						state_6();
						break;
					case 7:
						state_7();
						break;
					case 8:
						state_8();
						break;
					case 9:
						state_9();
						break;
					case 10:
						state_10();
						break;
					case 11:
						state_11();
						break;
					case 12:
						state_12();
						break;
					case 13:
						state_13();
						break;
					default:
						break;
				}
            }
            inputReader.close();
            outputWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private void translate(int production) {
Collections.reverse(reduce);
        reduce.clear();
        return;
    }

    private void output(Symbol left) throws IOException {
        String out = left.getClassName() + "->";
        for (Symbol symbol : reduce) {
            out += symbol.getClassName() + " ";
        }
        System.out.println(out);
        if(outputWriter!=null){
            outputWriter.write(out+"\n");
        }
        return;
    }

    private void end() {

    }

    private Symbol readToken() throws Exception {
        String line = inputReader.readLine();
        if (line == null) {
            return null;
        }
        int lp = line.indexOf('(');
        int rp = line.indexOf(')');
        if (lp == -1 || rp == -1 || (!line.contains(TOKEN_SPLITER))) {
            throw new Exception("Token file wrong format!");
        }
        String[] toks = line.substring(lp + 1, rp).split(",");
        if (toks.length != TOKEN_TOKS_NUMBER) {
            throw new Exception("Token file wrong format!");
        }
        raw = toks[0];
        innerCode = Integer.valueOf(toks[2]);
        debug("Read token:<" + toks[0] + "," + toks[1] + "," + toks[2] + ">");
        return (Symbol) Class.forName(this.getClass().getPackage().getName() + "." + toks[1]).newInstance();
    }

    private void debug(String content) {
        if (debug) {
            System.out.println("[DEBUG]" + content);
        }
    }

    private void parseCmdArgs(String[] args) {
        if (args.length < 1) {

            System.out.println("Token file generated by lexical scanner unspecified.");
            System.out.println("Using -h for help.");
            System.exit(1);
        }
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ("-h".equals(arg)) {
                System.out.println("Usage : java Parser [Options] FILE");
                System.out.println("Options : ");
                System.out.println("\t-o\tOUTPUT\tSpecify the output path of file which contains AST.\n\t\t\t\tIf unspecified,the AST will only be printed on SYSOUT.");
                System.out.println("\t-d\t\t\tShow debug information.");
                System.out.println("\t-h\t\t\tShow help document.");
                System.out.println("\t-v\t\t\tShow version information.");
                System.exit(0);
            } else if ("-v".equals(arg)) {
                System.out.println("Parser generated by DokymeYacc.2018/1/2.\nHappy new year~");
                System.exit(0);
            } else if ("-o".equals(arg)) {
                i++;
                try {
                    outputPath = args[i];
                    outputWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath)));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            } else if ("-d".equals(arg)) {
                debug = true;
            } else {
                if (i != args.length - 1) {
                    System.out.println("Wrong arguement for '" + arg + "'");
                    System.out.println("Using -h for help.");
                } else {
                    inputPath = args[i];
                    try {
                        inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath)));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                }
            }
        }
    }

	public void state_0() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "MULTIPLY":
				symbolStack.push(token);
				stateStack.push(4);
				break;
			case "ID":
				symbolStack.push(token);
				stateStack.push(5);
				break;
			default:
				break;
		}
	}
	public void state_1() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "ASSIGN":
				symbolStack.push(token);
				stateStack.push(6);
				break;
			case "DollarR_000":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new RIGHT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(5);
				break;
			default:
				break;
		}
	}
	public void state_2() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "DollarR_000":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new START());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(2);
				break;
			default:
				break;
		}
	}
	public void state_3() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "DollarR_000":
				end();
				break;
			default:
				break;
		}
	}
	public void state_4() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "MULTIPLY":
				symbolStack.push(token);
				stateStack.push(4);
				break;
			case "ID":
				symbolStack.push(token);
				stateStack.push(5);
				break;
			default:
				break;
		}
	}
	public void state_5() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "ASSIGN":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new LEFT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(4);
				break;
			case "DollarR_000":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new LEFT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(4);
				break;
			default:
				break;
		}
	}
	public void state_6() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "MULTIPLY":
				symbolStack.push(token);
				stateStack.push(11);
				break;
			case "ID":
				symbolStack.push(token);
				stateStack.push(12);
				break;
			default:
				break;
		}
	}
	public void state_7() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "ASSIGN":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new RIGHT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(5);
				break;
			case "DollarR_000":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new RIGHT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(5);
				break;
			default:
				break;
		}
	}
	public void state_8() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "ASSIGN":
				for(int i=0;i<2;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new LEFT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(3);
				break;
			case "DollarR_000":
				for(int i=0;i<2;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new LEFT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(3);
				break;
			default:
				break;
		}
	}
	public void state_9() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "DollarR_000":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new RIGHT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(5);
				break;
			default:
				break;
		}
	}
	public void state_10() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "DollarR_000":
				for(int i=0;i<3;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new START());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(1);
				break;
			default:
				break;
		}
	}
	public void state_11() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "MULTIPLY":
				symbolStack.push(token);
				stateStack.push(11);
				break;
			case "ID":
				symbolStack.push(token);
				stateStack.push(12);
				break;
			default:
				break;
		}
	}
	public void state_12() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "DollarR_000":
				for(int i=0;i<1;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new LEFT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(4);
				break;
			default:
				break;
		}
	}
	public void state_13() throws IOException {
		switch(token.getClass().getSimpleName().toLowerCase()) {
			case "DollarR_000":
				for(int i=0;i<2;i++) {
					reduce.add(symbolStack.pop());
					stateStack.pop();
				}
				symbolStack.push(new LEFT());
				switch(symbolStack.peek().getClass().getSimpleName()) {
					default:
						break;
				}
				output(symbolStack.peek());
				translate(3);
				break;
			default:
				break;
		}
	}
	public static void main(String[] args) {
	      Parser parser = new Parser();
	      parser.parseCmdArgs(args);
	      parser.run();
	}

//    public static void main(String[] args) {
//        Parser parser = new Parser();
//        parser.parseCmdArgs(args);
//        parser.run();
//    }
}
