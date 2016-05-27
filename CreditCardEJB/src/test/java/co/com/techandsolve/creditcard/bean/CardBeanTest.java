package co.com.techandsolve.creditcard.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;





import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.techandsolve.creditcard.entities.Card;

@RunWith(MockitoJUnitRunner.class)
public class CardBeanTest {

	@Mock
	EntityManager entityManager;
	
	@Mock
	TypedQuery<Card> query;
	
	@InjectMocks
	CardBean cardBean;
	
	public static final String CEDULA ="1234";
	
	
	@Test
	public void debeFiltrarLasTarjetasPorLaCedulaYRetornarlas(){
		
		//arrange
		List<Card> expected = new  ArrayList<Card>();
		expected.add(new Card()); 
		expected.add(new Card()); 
		
		Mockito.when(entityManager
				.createQuery("Select p From Card p Where p.cedula = :cedula", Card.class))
				.thenReturn(query);
		
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		//act
		List<Card> lstResult = cardBean.getCardByCC(CEDULA);
		
		//assert
		Assert.assertEquals(expected, lstResult);
		
		Mockito.verify(query).setParameter("cedula", CEDULA);
			
	}
	
	@Test
	public void debeAgregarLaBonificacionesDe2Millones(){
		//arrange
		List<Card> expected = new  ArrayList<Card>();
		expected.add(new Card()); 
		expected.add(new Card()); 
		
		Mockito.when(entityManager
				.createQuery("Update Card set bonus = :bonus Where p.cedula = :cedula And mount > 1000000", Card.class))
				.thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		//act
		cardBean.addBonus(CEDULA, 20000);

	}
	
	@Test
	public void debeCrearUnaTarjeta(){
		//arrange
		Card card = new Card();
		card.setBonus(0);
		card.setCedula("1115069076");
		card.setLabel("tarjeta visa express");
		card.setStatus(0);
		card.setMount(2);
		
		//act
		cardBean.create(card);
	}
	
}
