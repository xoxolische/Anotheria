package network.advanced;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandMapper {

	private Pattern p;
	private Command c;

	public CommandMapper(Pattern p, Command c) {
		this.p = p;
		this.c = c;
	}

	public boolean matches(String s) {
		Matcher m = p.matcher(s);
		if (m.matches())
			return true;

		return false;
	}

	public Pattern getP() {
		return p;
	}

	public void setP(Pattern p) {
		this.p = p;
	}

	public Command getC() {
		return c;
	}

	public void setC(Command c) {
		this.c = c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandMapper other = (CommandMapper) obj;
		if (c != other.c)
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}

}
