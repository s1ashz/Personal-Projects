using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PipeSpawner : MonoBehaviour {

	public float maxYpos;
	public float minYpos;
	public float SpawnTime;

	public GameObject SpikeToSpawn;

	// Use this for initialization
	void Start () {
		//StartSpawningPipes ();
	}
	
	// Update is called once per frame
	void Update () {

	}

	public void StopSpawningPipes() {
		CancelInvoke ("spawnPipe");
	}

	public void StartSpawningPipes() {
		InvokeRepeating ("spawnPipe", 0.2f, SpawnTime);
	}

	void spawnPipe() {
		Instantiate(SpikeToSpawn, new Vector3( transform.position.x, Random.Range(maxYpos, minYpos), 0), Quaternion.identity);

	}

}
