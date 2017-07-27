package pl.damiandziura.tictactoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAO
{
	private static final Logger LOGGER = Logger.getLogger(DAO.class.getName());

	/**************************
	 * DATABASE CONNECTION DATA
	 **************************/
	private static DAO instacja = new DAO();
	private List<score> list;
	private Connection DBConnection;
	private final String login = "root";
	private final String password = "damian13";
	private final String url = "jdbc:mysql://localhost:3306/scoreboard";

	public DAO()
	{
		connect();
	}

	public void Insert(String name, int moves)
	{
		String sql = "INSERT INTO scoreboard (name, moves) VALUES (?, ?)";

		try
		{
			PreparedStatement ps = DBConnection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, moves);
			LOGGER.log(Level.FINE, "Adding new record to the database: ", sql);
			ps.executeUpdate();
		} catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}

	public List<score> getTop10()
	{
		try
		{
			list = new ArrayList<score>();
			String sql = "SELECT name, moves from scoreboard ORDER BY moves ASC limit 10";

			PreparedStatement statement = DBConnection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				String s = result.getString("name");
				int i = result.getInt("moves");
				score Score = new score(s, i);
				list.add(Score);
			}
			LOGGER.log(Level.FINE, "Returning list of books");
		} catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return list;

	}

	private void connect()
	{
		LOGGER.log(Level.FINE, "Connecting to database");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			LOGGER.log(Level.FINE, "Connection estabilished");
		} catch (ClassNotFoundException cnfe)
		{
			LOGGER.log(Level.SEVERE, "Connection faild " + cnfe);
		}

		try
		{
			DBConnection = (Connection) DriverManager.getConnection(url, login, password);
			LOGGER.log(Level.FINE, "Connected to the Database");
		} catch (SQLException se)
		{
			LOGGER.log(Level.SEVERE, "No database connection " + se);
		}
	}

	public static DAO getDAO()
	{
		return instacja;
	}
}
