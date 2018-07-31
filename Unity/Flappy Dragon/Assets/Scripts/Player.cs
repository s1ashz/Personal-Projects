using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {

	public float upForce;
	private Rigidbody2D rb;
	private bool started;
	private bool gameOver;


	// Use this for initialization
	void Start () {
		rb = GetComponent<Rigidbody2D> ();
		started = false;
		gameOver = false;
		GameManager.instance.GameStart ();

	}
		
	// Update is called once per frame
	void Update () {

		if (gameOver) {
			if (!started) {
				started = true;
				rb.isKinematic = !started;
			}
			Flip ();
		}

		if (!started && !gameOver) {
			if (Input.GetMouseButtonDown (0)) {
				started = true;
				rb.isKinematic = !started;
			}
		} else {
			if (Input.GetMouseButtonDown (0) && !gameOver) {

				rb.velocity = new Vector2 (0, 0);
				rb.AddForce (new Vector2 ( 0, upForce ));
			}
		}


	}

	protected void Flip()    
	{
		transform.localRotation = Quaternion.Euler(180, 0, 0);
	}

	void OnCollisionEnter2D(Collision2D col) {
		gameOver = true;
		GameManager.instance.GameOver ();
	}

	void OnTriggerEnter2D(Collider2D col) {

		if (col.gameObject.tag == "Spikes" && !gameOver) {
			gameOver = true;
			GameManager.instance.GameOver ();
		}

	}



}
