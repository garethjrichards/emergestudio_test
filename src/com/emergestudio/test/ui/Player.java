/**
 * 
 */
package com.emergestudio.test.ui;

/**
 * TODO Fix the equals() and hashCode() methods.
 * 
 * @author jasonbruwer
 */
public class Player {
	private ITicTacToeSquare.Symbol symbol;
	
	private String name;
	private int id;
	
	/**
	 * @param idParam
	 * @param nameParam
	 * @param symbolParam
	 */
	public Player(
			int idParam,
			String nameParam,
			ITicTacToeSquare.Symbol symbolParam) 
	{
		super();
		
		this.name = nameParam;
		this.symbol = symbolParam;
		this.id = idParam;
	}

	/**
	 * @return the symbol
	 */
	public ITicTacToeSquare.Symbol getSymbol() 
	{
		return this.symbol;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object objParam) 
	{
		if (objParam == this) return true;
		if (objParam == null || objParam.getClass() != getClass())
			return false;

		Player player = (Player) objParam;

		return this.id == player.id && (this.name == player.name || (this.name != null && 
				this.name.equals(player.name))) && this.symbol == player.symbol;      

		// This code below would be for Apache commons lang api which would be easier
		// but I assume you want the basics.
		//
		// return new EqualsBuilder().
		// 		append(id, player.id).
		//      append(name, player.name).
		//      append(symbol, player.symbol).
		//      isEquals();       
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + this.symbol.hashCode();
		return result;

		// Again, below would be for Apache commons lang api
		//
		// return new HashCodeBuilder(13, 43).
		// 		append(id).
		// 		append(name).
		//      append(symbol).
		//	    toHashCode();	
	}
}
