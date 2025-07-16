from kafka import KafkaConsumer
import json
from app.config import BOOTSTRAP_SERVERS, AUDIO_TRANSCRIBED_TOPIC

def get_kafka_consumer():
    return KafkaConsumer(
        AUDIO_TRANSCRIBED_TOPIC,
        bootstrap_servers=BOOTSTRAP_SERVERS,
        auto_offset_reset='earliest',
        group_id='summarizer-group',
        value_deserializer=lambda m: json.loads(m.decode('utf-8'))
    )
