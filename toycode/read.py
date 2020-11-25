import numpy as np
from tqdm import tqdm
from scipy.io.wavfile import write
samplerate = 44100
wav_data = np.zeros((2871,128000))
with open('total.inp') as fp:
    for idx in tqdm(range(2871)):
        wav_data[idx] = fp.readline().split()
        write("./total/{}.wav".format(idx), samplerate, wav_data[idx])