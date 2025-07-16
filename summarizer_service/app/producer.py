from kafka import KafkaProducer
import json
from app.config import BOOTSTRAP_SERVERS, MEETING_SUMMARIZED_TOPIC

producer = KafkaProducer(
    bootstrap_servers=BOOTSTRAP_SERVERS,
    value_serializer=lambda m: json.dumps(m).encode('utf-8')
)

def send_summary(data: dict):
    producer.send(MEETING_SUMMARIZED_TOPIC, value=data)
    producer.flush()
