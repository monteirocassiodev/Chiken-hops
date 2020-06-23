package com.bsb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.TextureArrayData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;

public class Bsb extends ApplicationAdapter {

    SpriteBatch batch;
    private Texture[] galinha;
    private Texture fundo;
    private Texture[] trump;
    private Texture[] covid;
    private Texture[] faustao;

    private Texture[] covidemenor;
    private Texture[] covidmaior;
    private Texture[] covidmedio;


    private Random numeroRandomico;

    private BitmapFont fonte;
    private Circle galinhaCirculo;
    private Circle covid1circulo;
    private Circle covid2circulo;
    private Circle covid3circulo;
    private Circle covid4circulo;
    //private ShapeRenderer shape;


    // ATRIBUTOS DE CONFIGURAÇÕES//

    private int larguraDispositivo;
    private int alturaDispositivo;
    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialDaGalinhaVertical;
    private float posicaoInicalvertical;
    private float movimentohorizontal;
    private float movimentohorizontal2;
    private float movimentohorizontal3;
    private float movimentohorizontal4;
    private boolean marcouPonto = false;


    private float deltaTime;
    private float horizontalRandomico;
    private float verticalRandomico;

    private int estadodoJogo = 0; //  0  não inicado quando estado for 1 iniciado
    private int pontuacao = 0;


    //	CRIAÇÃO DE ELEMENTOS

    @Override
    public void create() {

        numeroRandomico = new Random();

        galinhaCirculo = new Circle();
			/*covid1circulo = new Circle();
			covid2circulo = new Circle();
			covid3circulo = new Circle();
			covid4circulo = new Circle();
			shape = new ShapeRenderer();*/

        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);

        batch = new SpriteBatch(); // imagem de plano de fundo
        galinha = new Texture[3];
        galinha[0] = new Texture("galinha_1.png");
        galinha[1] = new Texture("galinha_2.png");
        galinha[2] = new Texture("galinha_3.png");


        covidemenor = new Texture[3];
        covidemenor[0] = new Texture("1_covidmenor.png");
        covidemenor[1] = new Texture("2_covidmenor.png");
        covidemenor[2] = new Texture("1_covidmenor.png");

        covid = new Texture[3];
        covid[0] = new Texture("1_covid.png");
        covid[1] = new Texture("2_covid.png");
        covid[2] = new Texture("1_covid.png");

        covidmaior = new Texture[3];
        covidmaior[0] = new Texture("1_covidmenor.png");
        covidmaior[1] = new Texture("2_covidmenor.png");
        covidmaior[2] = new Texture("1_covidmenor.png");


        covidmedio = new Texture[3];
        covidmedio[0] = new Texture("1_covidmenor.png");
        covidmedio[1] = new Texture("2_covidmenor.png");
        covidmedio[2] = new Texture("1_covidmenor.png");


        fundo = new Texture("fundo_revital.png");

        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();

        // PARA MOVIMENTAÇÃO

        posicaoInicalvertical = alturaDispositivo / 5;
        movimentohorizontal = larguraDispositivo;


    }


    // RENDERIZAÇÃO

    @Override
    public void render() {

        deltaTime = Gdx.graphics.getDeltaTime();
        variacao += deltaTime * 10;
        if (variacao > 2) variacao = 0;

        if (estadodoJogo == 0) {

            if (Gdx.input.justTouched()) ;
            {

                estadodoJogo = 1;
            }


        } else {


            velocidadeQueda++;

           /* if (posicaoInicalvertical > 0 || velocidadeQueda < 0)
                posicaoInicalvertical = posicaoInicialDaGalinhaVertical - velocidadeQueda;*/


        }


        if (estadodoJogo == 1) {

            movimentohorizontal -= deltaTime * 100;
            movimentohorizontal2 -= deltaTime * 250;
            movimentohorizontal3 -= deltaTime * 100;
            movimentohorizontal4 -= deltaTime * 450;

            if (Gdx.input.justTouched()) { // batida de asa
                velocidadeQueda = -30;
            }

            if (posicaoInicalvertical > 450 || velocidadeQueda < 0)
                posicaoInicalvertical = posicaoInicalvertical - velocidadeQueda;

            // PERSONAGENS EM MOVIMENTO QUE VOAM

            if (movimentohorizontal < -covidmaior.length - 500) { // desaparecimento do personagem
                movimentohorizontal = larguraDispositivo; // movimento
                horizontalRandomico = numeroRandomico.nextInt(800) - 200;
                marcouPonto = false;
            }

            if (movimentohorizontal2 < -covidemenor.length - 500) { // desaparecimento do personagem
                movimentohorizontal2 = larguraDispositivo; // movimento
                horizontalRandomico = numeroRandomico.nextInt(800) - 200;
                marcouPonto = false;
            }
            if (movimentohorizontal3 < -covidmedio.length - 500) { // desaparecimento do personagem
                movimentohorizontal3 = larguraDispositivo; // movimento
                horizontalRandomico = numeroRandomico.nextInt(800) - 200;
                marcouPonto = false;
            }

            if (movimentohorizontal4 < -covidemenor.length - 500) { // desaparecimento do personagem
                movimentohorizontal4 = larguraDispositivo; // movimento
                horizontalRandomico = numeroRandomico.nextInt(800) - 200;
                marcouPonto = false;
            }


            // verifica a pontuação
            if (movimentohorizontal < 300) {
                if (!marcouPonto) {
                    pontuacao++;
                    marcouPonto = true;
                }

            }

            if (movimentohorizontal3 < 300) {
                if (!marcouPonto) {
                    pontuacao++;
                    marcouPonto = true;
                }

            }

            if (movimentohorizontal2 < 300) {
                if (!marcouPonto) {
                    pontuacao++;
                    marcouPonto = true;
                }

            }

            if (movimentohorizontal4 < 300) {
                if (!marcouPonto) {
                    pontuacao++;
                    marcouPonto = true;
                }

            }
        } else {  // executar tela de game over






        }

		batch.begin();

		batch.draw(fundo,0,0,larguraDispositivo,alturaDispositivo);


		batch.draw(covid[(int)variacao],movimentohorizontal,(1800)+verticalRandomico);
		batch.draw(covidemenor[(int)variacao],movimentohorizontal2,(1000)+verticalRandomico);
		batch.draw(covidmaior[(int)variacao],movimentohorizontal3,(500)+verticalRandomico);
		batch.draw(covidmedio[(int)variacao],movimentohorizontal4,(50)+verticalRandomico);


		// Galinha
		batch.draw(galinha[(int)variacao],300,posicaoInicalvertical);

		// Pontuação
		fonte.draw(batch,String.valueOf(pontuacao),larguraDispositivo/2,alturaDispositivo-100);


		batch.end();
		// fim da renderiazação

		galinhaCirculo=new

				Circle(300+galinha[0].getWidth()/2,posicaoInicalvertical+galinha[0].

				getHeight()/2,galinha[0].

				getHeight()/2);
		covid1circulo=new

				Circle(movimentohorizontal+covid[0].getHeight()/2,(1800)+verticalRandomico+covid[0].

				getWidth()/2,covid[0].

				getHeight());
		covid2circulo=new

				Circle(movimentohorizontal2+covidemenor[0].getHeight()/2,(1000)+verticalRandomico+covidemenor[0].

				getWidth()/2,covidemenor[0].

				getHeight()/2);
		covid3circulo=new

				Circle(movimentohorizontal3+covidmaior[0].getHeight()/2,(500)+verticalRandomico+covidmaior[0].

				getWidth()/2,covidmaior[0].

				getHeight()/2);
		covid4circulo=new

				Circle(movimentohorizontal4+covidmedio[0].getHeight()/2,(50)+verticalRandomico+covidmedio[0].

				getWidth()/2,covidmedio[0].

				getHeight()/2);

		//desenhar formas

		galinhaCirculo.set(300+galinha[0].

				getWidth()/2,posicaoInicalvertical+galinha[0].

				getHeight()/2,galinha[0].

				getHeight()/2);
		covid1circulo.set(movimentohorizontal+covid[0].

				getHeight()/2,(1800)+verticalRandomico+covid[0].

				getWidth()/2,covid[0].

				getHeight()/2);
		covid2circulo.set(movimentohorizontal2+covidemenor[0].

				getHeight()/2,(1000)+verticalRandomico+covidemenor[0].

				getWidth()/2,covidemenor[0].

				getHeight()/2);
		covid3circulo.set(movimentohorizontal3+covidmaior[0].

				getHeight()/2,(500)+verticalRandomico+covidmaior[0].

				getWidth()/2,covidmaior[0].

				getHeight()/2);
		covid4circulo.set(movimentohorizontal4+covidmedio[0].

				getHeight()/2,(50)+verticalRandomico+covidmedio[0].

				getWidth()/2,covidmedio[0].

				getHeight()/2);


		// fim desenhar formas

			/*shape.begin(ShapeRenderer.ShapeType.Filled);

			shape.circle(galinhaCirculo.x , galinhaCirculo.y, galinhaCirculo.radius);
			shape.setColor(Color.WHITE);
			shape.circle(covid1circulo.x,covid1circulo.y,covid1circulo.radius);
			shape.setColor(Color.YELLOW);
			shape.circle(covid2circulo.x,covid2circulo.y,covid2circulo.radius);
			shape.setColor(Color.BLUE);
			shape.circle(covid3circulo.x,covid3circulo.y,covid3circulo.radius);
			shape.setColor(Color.GREEN);
			shape.circle(covid4circulo.x,covid4circulo.y,covid4circulo.radius);
			shape.setColor(Color.GRAY);

			shape.end();*/

		// TESTE DE COLISÃO

		if(Intersector.overlaps(galinhaCirculo,covid1circulo)||Intersector.overlaps(galinhaCirculo,covid2circulo)||Intersector.overlaps(galinhaCirculo,covid3circulo)||Intersector.overlaps(galinhaCirculo,covid4circulo))


			estadodoJogo=2;







    }


















}


// até aqui galinha




















	
