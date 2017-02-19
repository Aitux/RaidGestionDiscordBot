import java.io.PrintStream;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;

public class DiscordStream {
	private TextChannel channel;
	private RedirectStream systemOut;
	private RedirectStream systemErr;
	private PrivateChannel PrivChan;

	/**
	 * Creates a new instance of the DiscordStream along with with the required
	 * RedirectStreams.
	 * <p>
	 * 
	 * @param channel
	 *            The Discord {@link net.dv8tion.jda.entities.TextChannel
	 *            TextChannel} to redirect output to.
	 * @throws NullPointerException
	 *             If the provided TextChannel is null.
	 */

	public DiscordStream(PrivateChannel channel, boolean enable) {

		setPrivate(channel);
		systemOut = new RedirectStream(System.out) {
			@Override
			protected void enableRedirect(boolean enable) {
				if (enable) {
					System.setOut(this);
				} else {
					System.setOut(getOut());
				}
			}
		};
		systemErr = new RedirectStream(System.err) {
			@Override
			protected void enableRedirect(boolean enable) {
				if (enable) {
					System.setErr(this);
				} else {
					System.setErr(getOut());
				}
			}
		};
		enableRedirect(enable);
	}

	public DiscordStream(TextChannel channel) {
		this(channel, false);

	}

	/**
	 * Creates a new instance of the DiscordStream along with with the required
	 * RedirectStreams. Automatically redirects STDout and STDerr.
	 * <p>
	 * 
	 * @param channel
	 *            The Discord {@link net.dv8tion.jda.entities.TextChannel
	 *            TextChannel} to redirect output to.
	 * @param enable
	 *            Used to start redirecting immediately instead of needing an
	 *            additional call to
	 *            {@link DiscordStream#enableRedirect(boolean)
	 *            enableRedirect(true)}.
	 * @throws NullPointerException
	 *             If the provided TextChannel is null.
	 */
	public DiscordStream(TextChannel channel, boolean enable) {
		setChannel(channel);
		systemOut = new RedirectStream(System.out) {
			@Override
			protected void enableRedirect(boolean enable) {
				if (enable) {
					System.setOut(this);
				} else {
					System.setOut(getOut());
				}
			}
		};
		systemErr = new RedirectStream(System.err) {
			@Override
			protected void enableRedirect(boolean enable) {
				if (enable) {
					System.setErr(this);
				} else {
					System.setErr(getOut());
				}
			}
		};
		enableRedirect(enable);
	}

	/**
	 * Sends a message to both the Console and to the Discord
	 * {@link net.dv8tion.jda.entities.TextChannel TextChannel}.
	 *
	 * @param string
	 *            The message which to send to the Console and Discord.
	 */
	public void print(String string) {
		systemOut.print(string);
	}

	/**
	 * Sends a message with a break line to both the Console and to the Discord
	 * {@link net.dv8tion.jda.entities.TextChannel TextChannel}.
	 *
	 * @param string
	 *            The message which to send to the Console and Discord.
	 */
	public void println(String string) {
		systemOut.println(string);
	}

	public void printPrivate(String s) {
		PrivChan.sendMessage(s);
	}

	public void setPrivate(PrivateChannel s) {
		this.PrivChan = s;
	}

	/**
	 * Sends a break line to both the Console and to the Discord
	 * {@link net.dv8tion.jda.entities.TextChannel TextChannel}.
	 */
	public void println() {
		systemOut.println();
	}

	/**
	 * The {@link net.dv8tion.jda.entities.TextChannel TextChannel} which
	 * {@link System#out System.out} and {@link System#err System.err} are
	 * redirected to.
	 *
	 * @return The console output {@link net.dv8tion.jda.entities.TextChannel
	 *         TextChannel}.
	 */
	public TextChannel getChannel() {
		return channel;
	}

	/**
	 * Sets the {@link net.dv8tion.jda.entities.TextChannel TextChannel} that
	 * the Console output should be redirected to.
	 *
	 * @param channel
	 *            The TextChannel to redirect console output to.
	 * @throws NullPointerException
	 *             If the provided TextChannel is null.
	 */
	public void setChannel(TextChannel channel) {
		if (channel == null)
			throw new NullPointerException("Cannot redirect Console output to a null TextChannel!");
		this.channel = channel;
	}

	/**
	 * Enables the redirection of {@link System#out System.out} and
	 * {@link System#err System.err} if the boolean param is true.<br>
	 * If it is false, this method will restore {@link System#out System.out}
	 * and {@link System#err System.err} to their original PrintStream instances
	 * and stop redirecting to Discord.
	 *
	 * @param enable
	 *            True: Redirect output to Discord, False: Do not redirect to
	 *            Discord.
	 */
	public void enableRedirect(boolean enable) {
		systemOut.enableRedirect(enable);
		systemErr.enableRedirect(enable);
	}

	public void printToDiscord(String s) {
		channel.sendMessage(s);
	}

	private abstract class RedirectStream extends PrintStream {

		public RedirectStream(PrintStream out) {
			super(out);
		}

		protected abstract void enableRedirect(boolean enable);

		@Override
		public void println() {
			((PrintStream) out).println();
			DiscordStream.this.printToDiscord("\n");
		}

		@Override
		public void println(String s) {
			s = s == null ? "null" : s;
			((PrintStream) out).println(s);
			DiscordStream.this.printToDiscord(s + "\n");
		}

		@Override
		public void print(String s) {
			s = s == null ? "null" : s;
			((PrintStream) out).print(s);
			DiscordStream.this.printToDiscord(s);
		}

		public PrintStream getOut() {
			return (PrintStream) out;
		}
	}
}
