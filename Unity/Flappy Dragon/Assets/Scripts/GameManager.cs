using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour {

	public static GameManager instance;
	bool gameOver;

	void Awake() {
		DontDestroyOnLoad (this.gameObject);
		if (instance == null) {
			instance = this;
		} else {
			Destroy (this.gameObject);
		}
	}
	// Use this for initialization
	void Start () {
		gameOver = true;
		//GameStart ();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	public void GameStart() {
		gameOver = true;
		GameObject.Find ("SpikeSpawner").GetComponent<PipeSpawner>().StartSpawningPipes();
		UIManager.instance.gameOverPanel.SetActive (false);
		UIManager.instance.playerDead.SetActive (false);
	}

	public void GameOver() {
		gameOver = false;
		GameObject.Find ("SpikeSpawner").GetComponent<PipeSpawner>().StopSpawningPipes();
		ScoreManager.instance.StopScore ();
		UIManager.instance.GameOver ();
		StartCoroutine(LateCall());
	}

	IEnumerator LateCall()
	{
		yield return new WaitForSeconds(1);
		Debug.Log (gameOver);
		if (!gameOver) {
			UIManager.instance.playerDead.SetActive (true);
		}
		//Do Function here...
	}
}

